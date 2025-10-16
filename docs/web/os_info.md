The OS information refers to the details of the operating system of the device on which the application using **KInfo** is
currently running

### Original source

The operating system information are retrieved from `UAParser.result.os` property

### KInfo's source

```kotlin
val os: Os = webInfo.os
```

### Properties

#### name

The name of the operating system

```kotlin
val name: String = os.name

println(name) //e.g. Windows
```

#### version

The version of the operating system

```kotlin
val version: String = os.version

println(version) //e.g. 10
```