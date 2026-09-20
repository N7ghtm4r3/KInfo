Represents common information about an installed application across different operating systems. This class provides 
standardized access to essential application details while allowing flexibility for OS-specific fields via an
additional information map

## Properties

### name

The name of the application

```kotlin
val installedApps = operatingSystem.queryInstalledApps()
val sample: ApplicationInfo = installedApps.first()

val name: String = sample.name

println(name) // e.g. MyApp
```

### version

The version of the application

```kotlin
val installedApps = operatingSystem.queryInstalledApps()
val sample: ApplicationInfo = installedApps.first()

val version: String = sample.version

println(version) // e.g. 1.0.0
```

### vendor

The vendor or publisher of the application

```kotlin
val installedApps = operatingSystem.queryInstalledApps()
val sample: ApplicationInfo = installedApps.first()

val vendor: String = sample.vendor

println(vendor) // e.g. Tecknobit
```

### timestamp

The installation or last modified timestamp of the application in milliseconds since epoch.

- On **Windows**, this corresponds to the application's install date
- On **Linux**, it represents the package's installation or last modified time
- On **macOS**, it reflects the last modification timestamp of the application bundle

```kotlin
val installedApps = operatingSystem.queryInstalledApps()
val sample: ApplicationInfo = installedApps.first()

val timestamp: Long = sample.timestamp

println(timestamp) // e.g. 1728800000000
```

### additionalInfo

A map containing additional application details such as install location, source, etc. 

Keys are field names, and values are corresponding details

```kotlin
val installedApps = operatingSystem.queryInstalledApps()
val sample: ApplicationInfo = installedApps.first()

val additionalInfo: Map<String, String> = sample.additionalInfo

println(additionalInfo) 
// e.g. {"installLocation"="/usr/share/code", "source"="official", "license"="MIT"}
```