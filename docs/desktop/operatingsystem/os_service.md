Represents an operating system service

## Properties

### name

The name of the service

```kotlin
val services = operatingSystem.services
val sample: OSService = services.first()

val name: String = sample.name

println(name) // e.g. nginx
```

### processID

The process ID associated with the service

```kotlin
val services = operatingSystem.services
val sample: OSService = services.first()

val processID: Int = sample.processID

println(processID) // e.g. 2156
```

### state

The current state of the service

#### ServiceState

| **state**   | **Description**                             |
|-------------|---------------------------------------------|
| **RUNNING** | The service is currently running            |
| **STOPPED** | The service is currently stopped            |
| **OTHER**   | The service is in an unknown or other state |

```kotlin
val services = operatingSystem.services
val sample: OSService = services.first()

val state: ServiceState = sample.state

println(state) // e.g. RUNNING
```