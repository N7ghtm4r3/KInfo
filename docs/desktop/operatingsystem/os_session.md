Represents a user session in the operating system, providing information about the session, including the user, terminal device,
login time, and host related to the session

## Properties

### userName

The username of the user in the session

```kotlin
val sessions = operatingSystem.services
val sample: OSSession = sessions.first()

val userName: String = sample.name

println(userName) // e.g. user
```

### terminalDevice

The terminal device associated with the session

```kotlin
val sessions = operatingSystem.services
val sample: OSSession = sessions.first()

val terminalDevice: String = sample.terminalDevice

println(terminalDevice) // e.g. /dev/pts/0
```

### loginTime

The login time of the user session, represented as a timestamp in milliseconds

```kotlin
val sessions = operatingSystem.services
val sample: OSSession = sessions.first()

val loginTime: Long = sample.loginTime

println(loginTime) // e.g. 1728903000000
```

### host

The host name or IP address of the system where the session is active

```kotlin
val sessions = operatingSystem.services
val sample: OSSession = sessions.first()

val host: String = sample.host

println(host) // e.g. 192.168.1.10
```