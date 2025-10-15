The CPU information refers to the details of the CPU of the device

### Original source

The CPU information are retrieved from `UAParser.result.cpu` interface

### KInfo's source

```kotlin
val cpu: CPU = webInfo.cpu
```

### Properties

#### architecture

The CPU architecture of the device

```kotlin
val architecture: String = cpu.architecture

println(architecture) //e.g. x86
```