# Overview

## Available information

On **iOS** target are available the below information:

| **Category**    | **Property**                                 | **Description**                                                                          | **Source**                                                            |
|-----------------|----------------------------------------------|------------------------------------------------------------------------------------------|-----------------------------------------------------------------------|
| **App Info**    | `appName`                                    | The name of the application                                                              | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | `bundleId`                                   | The unique identifier for the app bundle                                                 | `NSBundle.mainBundle.bundleIdentifier`                                |
|                 | `appVersion`                                 | The version of the app                                                                   | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | `appShortVersion`                            | The short version of the app                                                             | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | `isDebug`                                    | Indicates whether the app is running in debug mode                                       | `Platform.isDebugBinary`                                              |
| **Device Info** | `name`                                       | The name of the current device                                                           | `UIDevice.currentDevice.name`                                         |
|                 | `systemName`                                 | The name of the iOS operating system                                                     | `UIDevice.currentDevice.systemName`                                   |
|                 | `systemVersion`                              | The version of the iOS operating system                                                  | `UIDevice.currentDevice.systemVersion`                                |
|                 | `model`                                      | The model identifier of the current device                                               | `UIDevice.currentDevice.model`                                        |
|                 | `localizedModel`                             | The localized model name of the current device                                           | `UIDevice.currentDevice.localizedModel`                               |
|                 | `identifierForVendor`                        | The unique identifier for the vendor associated with the app                             | `UIDevice.currentDevice.identifierForVendor.UUIDString`               |
|                 | `isPhysicalDevice`                           | Indicates whether the current device is physical or a simulator                          | `NSProcessInfo.processInfo.environment`                               |
|                 | `isMultitaskingSupported`                    | Indicates whether the current iOS device supports multitasking                           | `UIDevice.currentDevice.isMultitaskingSupported()`                    |
|                 | `isGeneratingDeviceOrientationNotifications` | Indicates whether the current device is generating notifications for orientation changes | `UIDevice.currentDevice.isGeneratingDeviceOrientationNotifications()` |
|                 | `deviceOrientation`                          | Current orientation of the device                                                        | `IosDeviceOrientationImpl`                                            |
| **Locale**      | `locale`                                     | Represents the current language and region of the device                                 | `NSLocale.currentLocale`                                              |

## API source

The information are retrievable using the `IosInfo` API:

### Composable context

Retrieve a `KInfoState` instance inside **composable** context

```kotlin
val kInfoState = rememberKInfoState()
```

### Non-composable context

Retrieve a `KInfoState` instance inside **non-composable** context

```kotlin
val kInfoState = KInfoState()
```

### IosInfo

Retrieve a `IosInfo` instance from `kInfoState` instance

```kotlin

val iosInfo = kInfoState.iosInfo 
```

!!! Warning

    You can directly retrieve `iosInfo` just inside the `iosMain` module, in the `commonMain` module you have
    to use the [common usage](../usage.md) instead, or the application will crash