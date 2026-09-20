The information refer to the operating system version

### Original source

The operating system version information are retrieved from `OperatingSystem.OSVersionInfo` interface

### KInfo's source

```kotlin
val osVersionInfo = operatingSystem.versionInfo
```

### Properties

#### version

The version of the operating system

```kotlin
val version: String = osVersionInfo.version

println(version) // e.g. 11
```

#### codeName

The code name of the operating system version

```kotlin
val codeName: String = osVersionInfo.codeName

println(codeName) // e.g. Home
```

#### buildNumber

The build number of the operating system

```kotlin
val buildNumber: String = osVersionInfo.buildNumber

println(buildNumber) // e.g. 00000
```