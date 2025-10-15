The web information refers to those miscellaneous information provided by the browser

## userAgent

The user agent string representing the client's web browser and system

### Original source

The user agent is retrieved from `window.navigator.userAgent` property

### KInfo's source

```kotlin
val userAgent: String = webInfo.userAgent

println(userAgent)
// e.g. Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36
```