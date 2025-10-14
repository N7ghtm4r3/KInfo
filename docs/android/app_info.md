The application information refers to the details of the application in which `KInfo` is currently used

## appName

The name of the application

### Original source

The application name is retrieved from `PackageInfo.applicationInfo` property

### KInfo's source

```kotlin
val appName: String = androidInfo.appName

println(appName) // e.g. KInfoDemo
```

## packageName

The package name of the application

### Original source

The package name of the application is retrieved from `Context.packageName` property

### KInfo's source

```kotlin
val packageName: String = androidInfo.packageName

println(packageName) // e.g. com.tecknobit.kinfodemo
```

## versionName

The version name of the application

### Original source

The version name of the application is retrieved from `PackageInfo.versionName` property

### KInfo's source

```kotlin
val versionName: String = androidInfo.versionName

println(versionName) // e.g. 1.0.0
```

## versionCode

The version code of the application

### Original source

The version code of the application is retrieved from `PackageInfoCompat.getLongVersionCode(packageInfo)` property

### KInfo's source

```kotlin
val versionCode: String = androidInfo.versionCode

println(versionCode) // e.g. 1
```

## isDebug

Indicates whether the application is the debug build

### Original source

`isDebug` value is retrieved from `BuildConfig.DEBUG` property

### KInfo's source

```kotlin
val isDebug: Boolean = androidInfo.isDebug

println(isDebug) // e.g. true or false
```