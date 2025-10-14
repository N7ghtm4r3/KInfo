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