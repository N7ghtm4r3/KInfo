Information refer to the locale set on the device

## locale

Locale information (language and region)

### Original source

The locale value is retrieved with `LocaleManagerCompat.getSystemLocales` method

### KInfo's source

```kotlin
val locale: Locale = androidInfo.locale

println(locale) // e.g. Locale(languageCode=it, region=IT)
```