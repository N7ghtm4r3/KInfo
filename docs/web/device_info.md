The device information refers to the details of the device on which the application using **KInfo** is
currently running

### Original source

The device information are retrieved from `UAParser.result.device` interface

### KInfo's source

```kotlin
val device: Device = webInfo.device
```

### Properties

#### model

The model of the device

```kotlin
val model: String = device.model

println(model) //e.g. Xiaomi
```

#### type

The type of the device

```kotlin
val type: String = device.type

println(type) //e.g. mobile
```