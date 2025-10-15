Information refer to the locale set on the device

## locale

Represents the current language and region of the device

### Original source

The locale value is retrieved with `NSLocale.currentLocale` property

### KInfo's source

```kotlin
val locale: Locale = iosInfo.locale

println(locale) // e.g. Locale(languageCode=it, region=IT)
```