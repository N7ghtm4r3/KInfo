Represents the first active user session found in the native macOS `utmpx` records, providing its username, terminal device,
login time, and remote host

The `operatingSystem` instance is retrievable using the [Operating system API](index.md#operatingsystem-api).
The `utmpx` property returns one active record and does not select a session by the application's current user

!!! Warning

    Reading `operatingSystem.utmpx` throws an `IllegalStateException` if the records cannot be read or no active user session
    is found

## Properties

### userName

The username recorded for the session

```kotlin
val sample: MacOsOsSession = operatingSystem.utmpx

val userName: String = sample.userName

println(userName) // e.g. user
```

### terminalDevice

The terminal device recorded for the session, without adding a `/dev/` prefix

```kotlin
val sample: MacOsOsSession = operatingSystem.utmpx

val terminalDevice: String = sample.terminalDevice

println(terminalDevice) // e.g. ttys000
```

### loginTime

The login timestamp in milliseconds since the Unix epoch, converted from the native seconds and microseconds fields

```kotlin
val sample: MacOsOsSession = operatingSystem.utmpx

val loginTime: Long = sample.loginTime

println(loginTime) // e.g. 1760000000000
```

### host

The remote host name or address recorded for the session. Local sessions can have an empty value

```kotlin
val sample: MacOsOsSession = operatingSystem.utmpx

val host: String = sample.host

println(host) // e.g. 192.0.2.10
```
