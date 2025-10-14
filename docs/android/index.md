# Overview

## Available information

On **Android** target are available the below information:

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

## API source

The information are retrievable using the `AndroidInfo` API:

### Composable context

Retrieve a `KInfoState` instance inside **composable** context

```kotlin
val kInfoState = rememberKInfoState()
```

### Non-Composable context

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