package com.tecknobit.kinfo.utils

import com.tecknobit.kinfo.annotations.Resolver

/**
 * Method used to map CPU performance tiers to power-manager voltage-state table numbers
 *
 * Each eight-byte `acc-clusters` record starts with an unsigned table number followed by an unsigned performance tier
 * Empty input falls back to tier zero with table one and tier one with table five for the legacy E/P layout
 * A byte count not divisible by eight produces an empty list, while valid records retain their order and duplicates
 *
 * @param rawClusters The registry bytes describing the CPU clusters
 *
 * @return the pairs containing the performance tier first and the table number second as [List] of [Pair]
 *
 * @since 1.1.0
 */
@Resolver
internal fun resolveCpuClusterTables(
    rawClusters: ByteArray
): List<Pair<Int, Int>> {
    if (rawClusters.isEmpty())
        return listOf(0 to 1, 1 to 5)
    if (rawClusters.size % 8 != 0)
        return emptyList()

    return List(rawClusters.size / 8) { index ->
        val offset = index * 8
        val table = rawClusters[offset].toInt() and 0xFF
        val tier = rawClusters[offset + 1].toInt() and 0xFF

        tier to table
    }
}

/**
 * Method used to resolve the maximum nominal CPU frequency from eight-byte voltage-state records
 *
 * The first four bytes of each record contain an unsigned little-endian frequency
 * As a unit heuristic, positive raw values below 100,000,000 are treated as kilohertz and multiplied by one thousand
 * Other values are treated as hertz, and the largest normalized value is retained
 *
 * @param rawFrequency The registry voltage-state table
 *
 * @return the maximum frequency in hertz, or zero for an empty or malformed table, as [Long]
 *
 * @since 1.1.0
 */
@Resolver
internal fun resolveCpuTableFrequency(
    rawFrequency: ByteArray
): Long {
    if (rawFrequency.isEmpty() || rawFrequency.size % 8 != 0)
        return 0

    var maxFrequency = 0L
    for (offset in rawFrequency.indices step 8) {
        val frequency = resolveFreq(
            rawFreq = rawFrequency,
            offset = offset,
            range = 0 until 4
        )
        val frequencyHz = if (frequency in 1 until 100_000_000L)
            frequency * 1000
        else
            frequency

        maxFrequency = maxOf(maxFrequency, frequencyHz)
    }

    return maxFrequency
}

/**
 * Method used to associate ordered CPU performance tiers with nominal frequency tiers
 *
 * Distinct observed core ranks and power-manager tier keys are sorted separately, then paired by position
 * Their numeric identifiers need not match, allowing P/S ranks to map when no efficiency core rank is present
 * Different tier counts produce an array of zeros, while missing processor identifiers produce zero in their slot
 *
 * @param processorNumbers The logical CPU identifiers in output order
 * @param processorTiers The performance ranks read for the native CPU identifiers
 * @param nominalFrequencies The nominal frequencies in hertz keyed by power-manager tier
 *
 * @return the frequencies in the requested logical CPU order, with zero for unavailable mappings, as [LongArray]
 *
 * @since 1.1.0
 */
@Resolver
internal fun resolveCpuNominalFrequencies(
    processorNumbers: List<Int>,
    processorTiers: Map<Int, Int>,
    nominalFrequencies: Map<Int, Long>
): LongArray {
    val tiers = processorTiers.values.distinct().sorted()
    val frequencyTiers = nominalFrequencies.keys.sorted()
    if (tiers.size != frequencyTiers.size)
        return LongArray(processorNumbers.size)

    val frequenciesByTier = tiers.zip(frequencyTiers).associate { (tier, frequencyTier) ->
        tier to nominalFrequencies.getValue(
            key = frequencyTier
        )
    }

    return LongArray(processorNumbers.size) { index ->
        val tier = processorTiers[processorNumbers[index]]

        frequenciesByTier[tier] ?: 0
    }
}
