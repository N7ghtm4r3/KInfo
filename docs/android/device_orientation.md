Information refer to the current orientation the device has

## deviceOrientation

The current orientation of the device

### Original source

The orientation value is retrieved from `Context.resources.configuration.orientation` property

### KInfo's source

```kotlin
val deviceOrientation: DeviceOrientation = androidInfo.deviceOrientation
```

### Properties

#### isPortrait

Whether the device is currently in portrait mode

```kotlin
val isPortrait: Boolean = deviceOrientation.isPortrait

println(isPortrait) // true or false
```

#### isLandscape

Whether the device is currently in landscape mode

```kotlin
val isLandscape: Boolean = deviceOrientation.isLandscape

println(isLandscape) // true or false
```