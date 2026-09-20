# KInfo

![Static Badge](https://img.shields.io/badge/android-4280511051?link=https%3A%2F%2Fplay.google.com%2Fstore%2Fapps%2Fdetails%3Fid%3Dcom.tecknobit.ametista)

### Retrievable Information from `AndroidInfo`

| **Category**        | **Property**         | **Description**                                      | **Source**                      |
|---------------------|----------------------|------------------------------------------------------|---------------------------------|
| **App Info**        | [`appName`](../docs/android/app_info.md#appname)            | The name of the application                          | `PackageInfo.applicationInfo`   |
|                     | [`packageName`](../docs/android/app_info.md#packagename)        | The package name of the application                  | `Context.packageName`           |
|                     | [`versionName`](../docs/android/app_info.md#versionname)        | The version name of the application                  | `PackageInfo.versionName`       |
|                     | [`versionCode`](../docs/android/app_info.md#versioncode)        | The version code of the application                  | `PackageInfoCompat`             |
|                     | [`isDebug`](../docs/android/app_info.md#isdebug)            | Indicates whether the application is the debug build | `ApplicationInfo.flags`         |
| **OS Info**         | [`version`](../docs/android/os_info.md#version)            | Details about the Android OS version                 | `AndroidVersionImpl`            |
|                     | [`VERSION_CODES`](../docs/android/os_info.md#version_codes)      | Enumerated version codes for Android                 | `AndroidVersionCodeImpl`        |
|                     | [`androidId`](../docs/android/os_info.md#androidid)          | Unique Android ID of the device                      | `Settings.Secure.ANDROID_ID`    |
|                     | [`androidCodename`](../docs/android/os_info.md#androidcodename)    | Android codename of the device                       | Custom Logic                    |
| **Device Info**     | [`board`](../docs/android/device_info.md#board)              | The board name of the device hardware                | `Build.BOARD`                   |
|                     | [`bootloader`](../docs/android/device_info.md#bootloader)         | The version of the device bootloader                 | `Build.BOOTLOADER`              |
|                     | [`device`](../docs/android/device_info.md#device)             | The device name                                      | `Build.DEVICE`                  |
|                     | [`display`](../docs/android/device_info.md#display)            | The display identifier for the build                 | `Build.DISPLAY`                 |
|                     | [`fingerprint`](../docs/android/device_info.md#fingerprint)        | Unique identifier for the build fingerprint          | `Build.FINGERPRINT`             |
|                     | [`hardware`](../docs/android/device_info.md#hardware)           | The name of the device hardware                      | `Build.HARDWARE`                |
|                     | [`host`](../docs/android/device_info.md#host)               | The host name used to build the system               | `Build.HOST`                    |
|                     | [`id`](../docs/android/device_info.md#id)                 | The build ID for the software                        | `Build.ID`                      |
|                     | [`manufacturer`](../docs/android/device_info.md#manufacturer)       | The manufacturer name of the device                  | `Build.MANUFACTURER`            |
|                     | [`model`](../docs/android/device_info.md#model)              | The model name of the device                         | `Build.MODEL`                   |
|                     | [`brand`](../docs/android/device_info.md#brand)              | The brand name of the device                         | `Build.BRAND`                   |
|                     | [`product`](../docs/android/device_info.md#product)            | The product name of the device                       | `Build.PRODUCT`                 |
|                     | [`deviceOrientation`](../docs/android/device_info.md#deviceorientation)  | Current orientation of the device                    | `AndroidDeviceOrientation`      |
| **Supported ABIs**  | [`supportedAbis`](../docs/android/abis.md#supportedabis)      | List of supported ABIs for the device                | `Build.SUPPORTED_ABIS`          |
|                     | [`supported32BitAbis`](../docs/android/abis.md#supported32bitabis) | List of supported 32-bit ABIs for the device         | `Build.SUPPORTED_32_BIT_ABIS`   |
|                     | [`supported64BitAbis`](../docs/android/abis.md#supported64bitabis) | List of supported 64-bit ABIs for the device         | `Build.SUPPORTED_64_BIT_ABIS`   |
| **Tags**            | [`tags`](../docs/android/tags.md#tags)               | Comma-separated tags associated with the build       | `Build.TAGS`                    |
|                     | [`isPhysicalDevice`](../docs/android/tags.md#isphysicaldevice)   | Indicates if the device is physical or an emulator   | Custom Logic                    |
| **System Features** | [`systemFeatureList`](../docs/android/system_features.md#systemfeaturelist)  | List of system features available on the device      | `PackageManager.systemFeatures` |
| **Display Info**    | [`displayMetrics`](../docs/android/display_info.md#displaymetrics)     | Display metrics containing screen properties         | `AndroidDisplayMetricsImpl`     |
| **Locale Info**     | [`locale`](../docs/android/locale_info.md#locale)             | Locale information (language and region)             | `LocaleManagerCompat`           |

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