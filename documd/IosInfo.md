# KInfo

![Static Badge](https://img.shields.io/badge/ios-445E91?link=https%3A%2F%2Fimg.shields.io%2Fbadge%2Fandroid-4280511051)

### Retrievable Information from `IosInfo`

| **Category**    | **Property**                                 | **Description**                                                                           | **Source**                                                            |
|-----------------|----------------------------------------------|-------------------------------------------------------------------------------------------|-----------------------------------------------------------------------|
| **Device Info** | `name`                                       | The name of the current iOS device (e.g., "iPhone 12").                                   | `UIDevice.currentDevice.name`                                         |
|                 | `systemName`                                 | The name of the iOS operating system (e.g., "iOS").                                       | `UIDevice.currentDevice.systemName`                                   |
|                 | `systemVersion`                              | The version of the iOS operating system (e.g., "14.4").                                   | `UIDevice.currentDevice.systemVersion`                                |
|                 | `model`                                      | The model identifier of the current iOS device (e.g., "iPhone12,1").                      | `UIDevice.currentDevice.model`                                        |
|                 | `localizedModel`                             | The localized model name of the current iOS device (e.g., "iPhone").                      | `UIDevice.currentDevice.localizedModel`                               |
|                 | `identifierForVendor`                        | The unique identifier for the vendor associated with the app (e.g., "E123456789").        | `UIDevice.currentDevice.identifierForVendor.UUIDString`               |
|                 | `isPhysicalDevice`                           | Indicates whether the current device is physical or a simulator.                          | `NSProcessInfo.processInfo.environment`                               |
|                 | `isMultitaskingSupported`                    | Indicates whether the current iOS device supports multitasking.                           | `UIDevice.currentDevice.isMultitaskingSupported()`                    |
|                 | `isGeneratingDeviceOrientationNotifications` | Indicates whether the current device is generating notifications for orientation changes. | `UIDevice.currentDevice.isGeneratingDeviceOrientationNotifications()` |
|                 | `deviceOrientation`                          | Provides the current orientation of the iOS device.                                       | `IosDeviceOrientationImpl`                                            |
| **App Info**    | `appName`                                    | The name of the app (e.g., "MyApp").                                                      | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | `bundleId`                                   | The unique identifier for the app bundle (e.g., "com.example.myapp").                     | `NSBundle.mainBundle.bundleIdentifier`                                |
|                 | `appVersion`                                 | The version of the app (e.g., "1.0.0").                                                   | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | `appShortVersion`                            | The short version of the app (e.g., "1.0").                                               | `NSBundle.mainBundle.infoDictionary`                                  |
|                 | `isDebug`                                    | Indicates whether the app is running in debug mode.                                       | `Platform.isDebugBinary`                                              |
| **Locale**      | `locale`                                     | Represents the current language and region of the device.                                 | `NSLocale.currentLocale`                                              |

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

Copyright © 2025 Tecknobit