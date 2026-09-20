# Overview

## Available information

On **iOS** target are available the below information:

| **Category**    | **Property**                                                                                              | **Description**                                                                          | **Source**                                                            |
|-----------------|-----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------|-----------------------------------------------------------------------|
| **App Info**    | [`appName`](app_info.md#appname)                                                                          | The name of the application                                                              | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | [`bundleId`](app_info.md#bundleid)                                                                        | The unique identifier for the app bundle                                                 | `NSBundle.mainBundle.bundleIdentifier`                                |
|                 | [`appVersion`](app_info.md#appversion)                                                                    | The version of the app                                                                   | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | [`appShortVersion`](app_info.md#appshortversion)                                                          | The short version of the app                                                             | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | [`isDebug`](app_info.md#isdebug)                                                                          | Indicates whether the app is running in debug mode                                       | `Platform.isDebugBinary`                                              |
| **Device Info** | [`name`](device_info.md#name)                                                                             | The name of the current device                                                           | `UIDevice.currentDevice.name`                                         |
|                 | [`systemName`](device_info.md#systemname)                                                                 | The name of the iOS operating system                                                     | `UIDevice.currentDevice.systemName`                                   |
|                 | [`systemVersion`](device_info.md#systemversion)                                                           | The version of the iOS operating system                                                  | `UIDevice.currentDevice.systemVersion`                                |
|                 | [`model`](device_info.md#model)                                                                           | The model identifier of the current device                                               | `UIDevice.currentDevice.model`                                        |
|                 | [`localizedModel`](device_info.md#localizedmodel)                                                         | The localized model name of the current device                                           | `UIDevice.currentDevice.localizedModel`                               |
|                 | [`identifierForVendor`](device_info.md#identifierforvendor)                                               | The unique identifier for the vendor associated with the app                             | `UIDevice.currentDevice.identifierForVendor.UUIDString`               |
|                 | [`isPhysicalDevice`](device_info.md#isphysicaldevice)                                                     | Indicates whether the current device is physical or a simulator                          | `NSProcessInfo.processInfo.environment`                               |
|                 | [`isMultitaskingSupported`](device_info.md#ismultitaskingsupported)                                       | Indicates whether the current iOS device supports multitasking                           | `UIDevice.currentDevice.isMultitaskingSupported()`                    |
|                 | [`isGeneratingDeviceOrientationNotifications`](device_info.md#isgeneratingdeviceorientationnotifications) | Indicates whether the current device is generating notifications for orientation changes | `UIDevice.currentDevice.isGeneratingDeviceOrientationNotifications()` |
|                 | [`deviceOrientation`](device_info.md#deviceorientation)                                                   | Current orientation of the device                                                        | `IosDeviceOrientationImpl`                                            |
| **Locale Info** | [`locale`](locale_info.md#locale)                                                                         | Represents the current language and region of the device                                 | `NSLocale.currentLocale`                                              |

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