Information refer to the display of the device

## displayMetrics

Display metrics containing screen properties

### Original source

The display metrics are retrieved from `android.util.DisplayMetrics` property

### KInfo's source

```kotlin
val displayMetrics: DisplayMetrics = androidInfo.displayMetrics
```

### Properties

#### widthInches

The width of the display in inches

```kotlin
val widthInches: Double = displayMetrics.widthInches

println(widthInches) // e.g. 2.4545454545454546
```

#### heightInches

The height of the display in inches

```kotlin
val heightInches: Double = displayMetrics.heightInches

println(heightInches) // e.g. 4.8954545454545455
```

#### xDpi

The screen's horizontal density in dots per inch (DPI)

```kotlin
val xDpi: Double = displayMetrics.xDpi

println(xDpi) // e.g. 440.0
```

#### yDpi

The screen's vertical density in dots per inch (DPI)

```kotlin
val yDpi: Double = displayMetrics.yDpi

println(yDpi) // e.g. 440.0
```