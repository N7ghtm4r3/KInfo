# KInfo

![Static Badge](https://img.shields.io/badge/android-4280511051?link=https%3A%2F%2Fplay.google.com%2Fstore%2Fapps%2Fdetails%3Fid%3Dcom.tecknobit.ametista)

### Retrievable Information from `AndroidInfo`

| **Category**         | **Property**         | **Description**                                    | **Source**                      |
|----------------------|----------------------|----------------------------------------------------|---------------------------------|
| **App Info**         | `appName`            | The name of the application                        | `PackageInfo.applicationInfo`   |
|                      | `packageName`        | The package name of the application                | `Context.packageName`           |
|                      | `versionName`        | The version name of the application                | `PackageInfo.versionName`       |
|                      | `versionCode`        | The version code of the application                | `PackageInfoCompat`             |
|                      | `isDebug`            | Indicates if the application is in debug mode      | `BuildConfig.DEBUG`             |
| **OS Info**          | `version`            | Details about the Android OS version               | `AndroidVersionImpl`            |
|                      | `VERSION_CODES`      | Enumerated version codes for Android               | `AndroidVersionCodeImpl`        |
|                      | `androidId`          | Unique Android ID of the device                    | `Settings.Secure.ANDROID_ID`    |
| **Device Info**      | `board`              | The board name of the device hardware              | `Build.BOARD`                   |
|                      | `bootloader`         | The version of the device bootloader               | `Build.BOOTLOADER`              |
|                      | `device`             | The device name                                    | `Build.DEVICE`                  |
|                      | `display`            | The display identifier for the build               | `Build.DISPLAY`                 |
|                      | `fingerprint`        | Unique identifier for the build fingerprint        | `Build.FINGERPRINT`             |
|                      | `hardware`           | The name of the device hardware                    | `Build.HARDWARE`                |
|                      | `host`               | The host name used to build the system             | `Build.HOST`                    |
|                      | `id`                 | The build ID for the software                      | `Build.ID`                      |
|                      | `manufacturer`       | The manufacturer name of the device                | `Build.MANUFACTURER`            |
|                      | `model`              | The model name of the device                       | `Build.MODEL`                   |
|                      | `brand`              | The brand name of the device                       | `Build.BRAND`                   |
|                      | `product`            | The product name of the device                     | `Build.PRODUCT`                 |
| **Supported ABIs**   | `supportedAbis`      | List of supported ABIs for the device              | `Build.SUPPORTED_ABIS`          |
|                      | `supported32BitAbis` | List of supported 32-bit ABIs for the device       | `Build.SUPPORTED_32_BIT_ABIS`   |
|                      | `supported64BitAbis` | List of supported 64-bit ABIs for the device       | `Build.SUPPORTED_64_BIT_ABIS`   |
| **Tags**             | `tags`               | Comma-separated tags associated with the build     | `Build.TAGS`                    |
|                      | `isPhysicalDevice`   | Indicates if the device is physical or an emulator | Custom Logic                    |
| **System Features**  | `systemFeatureList`  | List of system features available on the device    | `PackageManager.systemFeatures` |
| **Display**          | `displayMetrics`     | Display metrics containing screen properties       | `AndroidDisplayMetricsImpl`     |
| **Locale**           | `locale`             | Locale information (language and region)           | `LocaleManagerCompat`           |
| **Orientation**      | `deviceOrientation`  | Current orientation of the device                  | `AndroidDeviceOrientation`      |
| **Android Codename** | `androidCodename`    | Android codename of the device                     | Custom Logic                    |

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