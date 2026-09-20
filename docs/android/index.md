# Overview

## Available information

On **Android** target are available the below information:

| **Category**        | **Property**                                                | **Description**                                      | **Source**                      |
|---------------------|-------------------------------------------------------------|------------------------------------------------------|---------------------------------|
| **App Info**        | [`appName`](app_info.md#appname)                            | The name of the application                          | `PackageInfo.applicationInfo`   |
|                     | [`packageName`](app_info.md#packagename)                    | The package name of the application                  | `Context.packageName`           |
|                     | [`versionName`](app_info.md#versionname)                    | The version name of the application                  | `PackageInfo.versionName`       |
|                     | [`versionCode`](app_info.md#versioncode)                    | The version code of the application                  | `PackageInfoCompat`             |
|                     | [`isDebug`](app_info.md#isdebug)                            | Indicates whether the application is the debug build | `ApplicationInfo.flags`         |
| **OS Info**         | [`version`](os_info.md#version)                             | Details about the Android OS version                 | `AndroidVersionImpl`            |
|                     | [`VERSION_CODES`](os_info.md#version_codes)                 | Enumerated version codes for Android                 | `AndroidVersionCodeImpl`        |
|                     | [`androidId`](os_info.md#androidid)                         | Unique Android ID of the device                      | `Settings.Secure.ANDROID_ID`    |
|                     | [`androidCodename`](os_info.md#androidcodename)             | Android codename of the device                       | Custom Logic                    |
| **Device Info**     | [`board`](device_info.md#board)                             | The board name of the device hardware                | `Build.BOARD`                   |
|                     | [`bootloader`](device_info.md#bootloader)                   | The version of the device bootloader                 | `Build.BOOTLOADER`              |
|                     | [`device`](device_info.md#device)                           | The device name                                      | `Build.DEVICE`                  |
|                     | [`display`](device_info.md#display)                         | The display identifier for the build                 | `Build.DISPLAY`                 |
|                     | [`fingerprint`](device_info.md#fingerprint)                 | Unique identifier for the build fingerprint          | `Build.FINGERPRINT`             |
|                     | [`hardware`](device_info.md#hardware)                       | The name of the device hardware                      | `Build.HARDWARE`                |
|                     | [`host`](device_info.md#host)                               | The host name used to build the system               | `Build.HOST`                    |
|                     | [`id`](device_info.md#id)                                   | The build ID for the software                        | `Build.ID`                      |
|                     | [`manufacturer`](device_info.md#manufacturer)               | The manufacturer name of the device                  | `Build.MANUFACTURER`            |
|                     | [`model`](device_info.md#model)                             | The model name of the device                         | `Build.MODEL`                   |
|                     | [`brand`](device_info.md#brand)                             | The brand name of the device                         | `Build.BRAND`                   |
|                     | [`product`](device_info.md#product)                         | The product name of the device                       | `Build.PRODUCT`                 |
|                     | [`deviceOrientation`](device_info.md#deviceorientation)     | Current orientation of the device                    | `AndroidDeviceOrientation`      |
| **Supported ABIs**  | [`supportedAbis`](abis.md#supportedabis)                    | List of supported ABIs for the device                | `Build.SUPPORTED_ABIS`          |
|                     | [`supported32BitAbis`](abis.md#supported32bitabis)          | List of supported 32-bit ABIs for the device         | `Build.SUPPORTED_32_BIT_ABIS`   |
|                     | [`supported64BitAbis`](abis.md#supported64bitabis)          | List of supported 64-bit ABIs for the device         | `Build.SUPPORTED_64_BIT_ABIS`   |
| **Tags**            | [`tags`](tags.md#tags)                                      | Comma-separated tags associated with the build       | `Build.TAGS`                    |
|                     | [`isPhysicalDevice`](tags.md#isphysicaldevice)              | Indicates if the device is physical or an emulator   | Custom Logic                    |
| **System Features** | [`systemFeatureList`](system_features.md#systemfeaturelist) | List of system features available on the device      | `PackageManager.systemFeatures` |
| **Display Info**    | [`displayMetrics`](display_info.md#displaymetrics)          | Display metrics containing screen properties         | `AndroidDisplayMetricsImpl`     |
| **Locale Info**     | [`locale`](locale_info.md#locale)                           | Locale information (language and region)             | `LocaleManagerCompat`           |

## API source

The information are retrievable using the `AndroidInfo` API:

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

### AndroidInfo

Retrieve a `AndroidInfo` instance from `kInfoState` instance

```kotlin

val androidInfo = kInfoState.androidInfo 
```

!!! Warning

    You can directly retrieve `androidInfo` just inside the `androidMain` module, in the `commonMain` module you have
    to use the [common usage](../usage.md) instead, or the application will crash