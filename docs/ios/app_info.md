The application information refers to the details of the application in which `KInfo` is currently used

## appName

The name of the application

### Original source

The application name is retrieved from `NSBundle.mainBundle.infoDictionary.CFBundleDisplayName` or
`NSBundle.mainBundle.infoDictionary.CFBundleName` property

### KInfo's source

```kotlin
val appName: String = iosInfo.appName

println(appName) // e.g. KInfoDemo
```

## bundleId

The unique identifier for the app bundle

### Original source

The unique identifier is retrieved from `NSBundle.mainBundle.bundleIdentifier` property

### KInfo's source

```kotlin
val bundleId: String = iosInfo.bundleId

println(bundleId) // e.g. com.example.myapp
```

## appVersion

The version of the app

### Original source

The version of the app is retrieved from `NSBundle.mainBundle.infoDictionary.CFBundleVersion` property

### KInfo's source

```kotlin
val appVersion: String = iosInfo.appVersion

println(appVersion) // e.g. 1.0.0
```

## appShortVersion

The short version of the app

### Original source

The short version of the app is retrieved from `NSBundle.mainBundle.infoDictionary.CFBundleShortVersionString` property

### KInfo's source

```kotlin
val appShortVersion: String = iosInfo.appShortVersion

println(appShortVersion) // e.g. 1.0
```

## isDebug

Indicates whether the app is running in debug mode

### Original source

The value is retrieved from `Platform.isDebugBinary` property

### KInfo's source

```kotlin
val isDebug: Boolean = iosInfo.isDebug

println(isDebug) // true or false
```