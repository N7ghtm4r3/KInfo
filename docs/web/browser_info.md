Information refer to the browser where the application is running

### Original source

The browser information are retrieved from `UAParser.result.browser` interface

### KInfo's source

```kotlin
val browser: Browser = webInfo.browser
```

### Properties

#### name

The name of the browser  

```kotlin
val name: String = browser.name

println(name) //e.g. Chrome
```

#### version

The version of the browser

```kotlin
val version: String = browser.version

println(version) //e.g. 91.0.4472.124
```

#### major

The major version number of the browser

```kotlin
val major: String = browser.major

println(major) //e.g. 91
```

#### type

The type of current browser

```kotlin
val type: String = browser.type

println(type) // (email, inapp, crawler)
```