The OS information refers to the details of the operating system of the device on which the application using KInfo is 
currently running

## version

Details about the Android OS version

### Original source

The details about the Android OS version are retrieved from `AndroidVersionImpl` class

### KInfo's source

```kotlin
val version: Version = androidInfo.version
```

#### Version properties

- `baseOs` the base operating system version of the Android device, when **empty** means that is an Android stock version

    ```kotlin
    val baseOs: String = version.baseOs
    
    println(baseOs) // e.g. empty or similar to R16NW/G960FXXU2BRJ6
    ```