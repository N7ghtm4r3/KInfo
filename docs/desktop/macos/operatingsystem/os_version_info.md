The information refer to the operating system version on native macOS

## API source

The information are retrievable using the [Operating system API](index.md#operatingsystem-api)

```kotlin
val osVersionInfo: MacOsVersionInfo = operatingSystem.operatingSystemVersion
```

## Properties

### version

The operating system version formatted as `major.minor.patch`

```kotlin
val version: String? = osVersionInfo.version

println(version) // e.g. 14.0.0
```

### codeName

The code name associated with the operating system version, or `Unknown` when the version is not recognized

```kotlin
val codeName: String? = osVersionInfo.codeName

println(codeName) // e.g. Sonoma
```

### buildNumber

The operating system build number, or `Unknown` when the native query cannot retrieve it

```kotlin
val buildNumber: String? = osVersionInfo.buildNumber

println(buildNumber) // e.g. 23A344
```
