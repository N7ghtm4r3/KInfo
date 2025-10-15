Represents the details of a physical memory module installed in the system, providing information such as the 
memory's bank label, capacity, clock speed, manufacturer, memory type, part number, and serial number

## Properties

### bankLabel

The label or identifier of the memory bank where the module is located

```kotlin
val physicalMemory = globalMemory.physicalMemory
val sample: PhysicalMemory = physicalMemory.first()

val bankLabel: String = sample.bankLabel

println(bankLabel) // e.g. P0 CHANNEL A
```

### capacity

The total capacity of the memory module (in bytes)

```kotlin
val physicalMemory = globalMemory.physicalMemory
val sample: PhysicalMemory = physicalMemory.first()

val capacity: Long = sample.capacity

println(capacity) // e.g. 17179869184
```

### clockSpeed

The clock speed of the memory module (in MHz)

```kotlin
val physicalMemory = globalMemory.physicalMemory
val sample: PhysicalMemory = physicalMemory.first()

val clockSpeed: Long = sample.clockSpeed

println(clockSpeed) // e.g. 2667000000
```

### manufacturer

The manufacturer of the memory module

```kotlin
val physicalMemory = globalMemory.physicalMemory
val sample: PhysicalMemory = physicalMemory.first()

val manufacturer: String = sample.manufacturer

println(manufacturer) // e.g. Corsair
```

### memoryType

The type of memory

```kotlin
val physicalMemory = globalMemory.physicalMemory
val sample: PhysicalMemory = physicalMemory.first()

val memoryType: String = sample.memoryType

println(memoryType) // e.g. DDR4
```

### partNumber

The part number of the memory module

```kotlin
val physicalMemory = globalMemory.physicalMemory
val sample: PhysicalMemory = physicalMemory.first()

val partNumber: String = sample.partNumber

println(partNumber) // e.g. CMK16GX4M2B3200C16
```

### serialNumber

The serial number of the memory module

```kotlin
val physicalMemory = globalMemory.physicalMemory
val sample: PhysicalMemory = physicalMemory.first()

val serialNumber: String = sample.serialNumber

println(serialNumber) // e.g. 00000000
```