Additional information describe the current build

## tags

Comma-separated tags associated with the build

### Original source

The tags string is retrieved from `Build.TAGS` property

### KInfo's source

```kotlin
val tags: String = androidInfo.tags

println(board) // e.g. release-keys
```

## isPhysicalDevice

Indicates if the device is physical or an emulator

### KInfo's source

```kotlin
val isPhysicalDevice: Boolean = androidInfo.isPhysicalDevice

println(isPhysicalDevice) // true or false
```