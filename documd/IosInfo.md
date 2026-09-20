# KInfo

![Static Badge](https://img.shields.io/badge/ios-445E91?link=https%3A%2F%2Fimg.shields.io%2Fbadge%2Fandroid-4280511051)

### Retrievable Information from `IosInfo`

| **Category**    | **Property**                                 | **Description**                                                                          | **Source**                                                            |
|-----------------|----------------------------------------------|------------------------------------------------------------------------------------------|-----------------------------------------------------------------------|
| **App Info**    | [`appName`](../docs/ios/app_info.md#appname)                                    | The name of the application                                                              | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | [`bundleId`](../docs/ios/app_info.md#bundleid)                                   | The unique identifier for the app bundle                                                 | `NSBundle.mainBundle.bundleIdentifier`                                |
|                 | [`appVersion`](../docs/ios/app_info.md#appversion)                                 | The version of the app                                                                   | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | [`appShortVersion`](../docs/ios/app_info.md#appshortversion)                            | The short version of the app                                                             | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | [`isDebug`](../docs/ios/app_info.md#isdebug)                                    | Indicates whether the app is running in debug mode                                       | `Platform.isDebugBinary`                                              |
| **Device Info** | [`name`](../docs/ios/device_info.md#name)                                       | The name of the current device                                                           | `UIDevice.currentDevice.name`                                         |
|                 | [`systemName`](../docs/ios/device_info.md#systemname)                                 | The name of the iOS operating system                                                     | `UIDevice.currentDevice.systemName`                                   |
|                 | [`systemVersion`](../docs/ios/device_info.md#systemversion)                              | The version of the iOS operating system                                                  | `UIDevice.currentDevice.systemVersion`                                |
|                 | [`model`](../docs/ios/device_info.md#model)                                      | The model identifier of the current device                                               | `UIDevice.currentDevice.model`                                        |
|                 | [`localizedModel`](../docs/ios/device_info.md#localizedmodel)                             | The localized model name of the current device                                           | `UIDevice.currentDevice.localizedModel`                               |
|                 | [`identifierForVendor`](../docs/ios/device_info.md#identifierforvendor)                        | The unique identifier for the vendor associated with the app                             | `UIDevice.currentDevice.identifierForVendor.UUIDString`               |
|                 | [`isPhysicalDevice`](../docs/ios/device_info.md#isphysicaldevice)                           | Indicates whether the current device is physical or a simulator                          | `NSProcessInfo.processInfo.environment`                               |
|                 | [`isMultitaskingSupported`](../docs/ios/device_info.md#ismultitaskingsupported)                    | Indicates whether the current iOS device supports multitasking                           | `UIDevice.currentDevice.isMultitaskingSupported()`                    |
|                 | [`isGeneratingDeviceOrientationNotifications`](../docs/ios/device_info.md#isgeneratingdeviceorientationnotifications) | Indicates whether the current device is generating notifications for orientation changes | `UIDevice.currentDevice.isGeneratingDeviceOrientationNotifications()` |
|                 | [`deviceOrientation`](../docs/ios/device_info.md#deviceorientation)                          | Current orientation of the device                                                        | `IosDeviceOrientationImpl`                                            |
| **Locale Info** | [`locale`](../docs/ios/locale_info.md#locale)                                     | Represents the current language and region of the device                                 | `NSLocale.currentLocale`                                              |

## Support

If you need help using the library or encounter any problems or bugs, please contact us via the
following links:

- Support via <a href="mailto:infotecknobitcompany@gmail.com">email</a>
- Support via <a href="https://github.com/N7ghtm4r3/KInfo/issues/new">GitHub</a>

Thank you for your help!

## Donations

If you want support project and developer

| Crypto                                                                                              | Address                                          | Network  |
|-----------------------------------------------------------------------------------------------------|--------------------------------------------------|----------|
| ![](https://img.shields.io/badge/Bitcoin-000000?style=for-the-badge&logo=bitcoin&logoColor=white)   | **3H3jyCzcRmnxroHthuXh22GXXSmizin2yp**           | Bitcoin  |
| ![](https://img.shields.io/badge/Ethereum-3C3C3D?style=for-the-badge&logo=Ethereum&logoColor=white) | **0x1b45bc41efeb3ed655b078f95086f25fc83345c4**   | Ethereum |
| ![](https://img.shields.io/badge/Solana-000?style=for-the-badge&logo=Solana&logoColor=9945FF)       | **AtPjUnxYFHw3a6Si9HinQtyPTqsdbfdKX3dJ1xiDjbrL** | Solana   |

If you want support project and developer
with <a href="https://www.paypal.com/donate/?hosted_button_id=5QMN5UQH7LDT4">PayPal</a>

Copyright © 2026 Tecknobit