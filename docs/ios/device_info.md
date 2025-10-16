The device information refers to the details of the device on which the application using **KInfo** is
currently running

## name

The name of the current device

### Original source

The name of the device is retrieved from `UIDevice.currentDevice.name` property

### KInfo's source

```kotlin
val name: String = iosInfo.name

println(name) // e.g. iPhone 12
```

## systemName

The name of the iOS operating system

### Original source

The name of the iOS is retrieved from `UIDevice.currentDevice.systemName` property

### KInfo's source

```kotlin
val systemName: String = iosInfo.systemName

println(systemName) // e.g. iOS
```

## systemVersion

The version of the iOS operating system

### Original source

The iOS version is retrieved from `UIDevice.currentDevice.systemVersion` property

### KInfo's source

```kotlin
val systemVersion: String = iosInfo.systemVersion

println(systemVersion) // e.g. 16
```

## model

The model identifier of the current device

### Original source

The model identifier is retrieved from `UIDevice.currentDevice.model` property

### KInfo's source

```kotlin
val model: String = iosInfo.model

println(model) // e.g. iPhone12,1
```

## localizedModel

The localized model name of the current device

### Original source

The localized model name is retrieved from `UIDevice.currentDevice.localizedModel` property

### KInfo's source

```kotlin
val localizedModel: String = iosInfo.localizedModel

println(localizedModel) // e.g. iPhone
```

## identifierForVendor

The unique identifier for the vendor associated with the app

### Original source

The identifier for the vendor is retrieved from `UIDevice.currentDevice.identifierForVendor.UUIDString` property

### KInfo's source

```kotlin
val identifierForVendor: String = iosInfo.identifierForVendor

println(identifierForVendor) // e.g. E123456789
```

## isPhysicalDevice

Indicates whether the current device is physical or a simulator

### Original source

The value is retrieved from `NSProcessInfo.processInfo.environment` property

### KInfo's source

```kotlin
val isPhysicalDevice: Boolean = iosInfo.isPhysicalDevice

println(isPhysicalDevice) // true or false
```

## isMultitaskingSupported

Indicates whether the current iOS device supports multitasking

### Original source

The value is retrieved from `UIDevice.currentDevice.isMultitaskingSupported()` method

### KInfo's source

```kotlin
val isMultitaskingSupported: Boolean = iosInfo.isMultitaskingSupported

println(isMultitaskingSupported) // true or false
```

## isGeneratingDeviceOrientationNotifications

Indicates whether the current device is generating notifications for orientation changes

### Original source

The value is retrieved from `UIDevice.currentDevice.isGeneratingDeviceOrientationNotifications()` method

### KInfo's source

```kotlin
val isGeneratingNotifications: Boolean = iosInfo.isGeneratingDeviceOrientationNotifications

println(isGeneratingNotifications) // true or false
```

## deviceOrientation

The current orientation of the device

### Original source

The orientation value is retrieved from `IosDeviceOrientationImpl` property

### KInfo's source

```kotlin
val deviceOrientation: DeviceOrientation = iosInfo.deviceOrientation
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