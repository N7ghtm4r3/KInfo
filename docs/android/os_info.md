The OS information refers to the details of the operating system of the device on which the application using **KInfo** is 
currently running

## version

Details about the Android OS version

### Original source

The details about the Android OS version are retrieved from `AndroidVersionImpl` class

### KInfo's source

```kotlin
val version: Version = androidInfo.version
```

### Properties

#### baseOs

The base operating system version of the Android device, when **empty** means that is an Android stock version

```kotlin
val baseOs: String = version.baseOs

println(baseOs) // e.g. empty or similar to R16NW/G960FXXU2BRJ6
```

#### sdkInt

The integer value of the current **Android SDK** version

```kotlin
val sdkInt: Int = version.sdkInt

println(sdkInt) // e.g. 36
```

#### codeName

The code name of the current Android version

```kotlin
val codeName: String = version.codeName

println(codeName) // e.g. Baklava
```

#### release

The string value representing the release version of the Android operating system

```kotlin
val release: String = version.release

println(release) // e.g. 16
```

#### incremental

The incremental version string, often used for identifying minor updates

```kotlin
val incremental: String = version.incremental

println(incremental) // e.g. 12096271
```

#### releaseOrCodeName

Value can be either the release version or the code name of the Android version

```kotlin
val releaseOrCodeName: String = version.releaseOrCodeName

println(releaseOrCodeName) // e.g. 16 or Baklava
```

#### releaseOrPreviewDisplay

Value can be either the release version or preview display name (if in preview mode)

```kotlin
val releaseOrPreviewDisplay: String = version.releaseOrPreviewDisplay

println(releaseOrPreviewDisplay) // e.g. Android 16 Preview
```

#### securityPatch

The security patch level of the current Android version

```kotlin
val securityPatch: String = version.securityPatch

println(securityPatch) // e.g. 2025-10-14
```

#### mediaPerformanceClass

The media performance class of the Android device (used to indicate the media performance tier)

```kotlin
val mediaPerformanceClass: Int = version.mediaPerformanceClass

println(mediaPerformanceClass) // e.g. from 0 to 3 or more
```

#### previewSdkInt

The SDK version of the preview release (if any), if **0** is a stable release, otherwise is an experimental release
and indicates which release step is the current release, such **Beta 2** 

```kotlin
val previewSdkInt: Int = version.previewSdkInt

println(previewSdkInt) // e.g. 2
```

## VERSION_CODES

Enumerated version codes for Android

### Original source

The enum entries are retrieved from `Build.VERSION_CODES` constants

### KInfo's source

```kotlin
val versionCode: VersionCode = androidInfo.VERSION_CODES
```

### Entries

#### CUR_DEVELOPMENT

The current development version of Android (for testing purposes)

```kotlin
println(versionCode.CUR_DEVELOPMENT) // e.g. 10000
```

#### CUPCAKE

The version code for **Android 1.5**

```kotlin
println(versionCode.CUPCAKE) // 3
```

#### DONUT

The version code for **Android 1.6**

```kotlin
println(versionCode.DONUT) // 4
```

#### LOLLIPOP

The version code for **Android Lollipop**

```kotlin
println(versionCode.LOLLIPOP) // 21
```

#### LOLLIPOP_MR1

The version code for **Android Lollipop MR1**

```kotlin
println(versionCode.LOLLIPOP_MR1) // 22
```

#### M

The version code for **Android Marshmallow**

```kotlin
println(versionCode.M) // 23
```

#### N

The version code for **Android Nougat**

```kotlin
println(versionCode.N) // 24
```

#### N_MR1

The version code for **Android Nougat MR1**

```kotlin
println(versionCode.N_MR1) // 25
```

#### O

The version code for **Android Oreo**

```kotlin
println(versionCode.O) // 26
```

#### O_MR1

The version code for **Android Oreo MR1**

```kotlin
println(versionCode.O_MR1) // 27
```

#### P

The version code for **Android Pie**

```kotlin
println(versionCode.P) // 28
```

#### Q

The version code for **Android 10**

```kotlin
println(versionCode.Q) // 29
```

#### R

The version code for **Android 11**

```kotlin
println(versionCode.R) // 30
```

#### S

The version code for **Android 12**

```kotlin
println(versionCode.S) // 32
```

#### S_V2

The version code for **Android 12**

```kotlin
println(versionCode.S_V2) // 32
```

#### TIRAMISU

The version code for **Android 13**

```kotlin
println(versionCode.TIRAMISU) // 33
```

#### UPSIDE_DOWN_CAKE

The version code for **Android 14**

```kotlin
println(versionCode.UPSIDE_DOWN_CAKE) // 34
```

## androidId

Unique Android ID of the device

### Original source

The value of the Android ID is retrieved from `Settings.Secure.ANDROID_ID` property

### KInfo's source

```kotlin
val androidId: String = androidInfo.androidId

println(androidId) // e.g. 016a501869178b55
```