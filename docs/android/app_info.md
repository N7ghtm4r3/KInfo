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

println(packageName) // e.g. com.tecknobit.kinfoDemo
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