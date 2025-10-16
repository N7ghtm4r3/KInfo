The ABIs information refers to supported **Application Binary Interface** the device supports

## supportedAbis

List of supported ABIs for the device

### Original source

The array is retrieved from `Build.SUPPORTED_ABIS` property

### KInfo's source

```kotlin
val supportedAbis: Array<String> = androidInfo.supportedAbis

println(supportedAbis) // e.g. [x86_64, arm64-v8a]
```

## supported32BitAbis

List of supported 32-bit ABIs for the device

### Original source

The array is retrieved from `Build.SUPPORTED_32_BIT_ABIS` property

### KInfo's source

```kotlin
val supported32BitAbis: Array<String> = androidInfo.supported32BitAbis

println(supported32BitAbis) // e.g. [x86]
```

## supported64BitAbis

List of supported 64-bit ABIs for the device

### Original source

The array is retrieved from `Build.SUPPORTED_64_BIT_ABIS` property

### KInfo's source

```kotlin
val supported64BitAbis: Array<String> = androidInfo.supported64BitAbis

println(supported64BitAbis) // e.g. [x86_64]
```