Information refer to hardware or software features of an Android device

## systemFeatureList

List of system features available on the device

### Original source

The feature list is retrieved from `PackageManager.systemAvailableFeatures` property

### KInfo's source

```kotlin
val systemFeatureList: List<String> = androidInfo.systemFeatureList

println(systemFeatureList) // e.g.  [android.hardware.sensor.proximity, etc...]
```