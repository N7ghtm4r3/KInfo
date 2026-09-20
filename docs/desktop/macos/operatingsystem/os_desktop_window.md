Represents a desktop window on native macOS, providing its identifier, owning application, executable path, process
identifier, list position, and visibility

The `operatingSystem` instance is retrievable using the [Operating system API](index.md#operatingsystem-api).
Use `visibleWindows` for on-screen windows or `allWindows` for all entries returned by the native window query

The examples below require at least one entry in `operatingSystem.visibleWindows`. Check that the list is not empty before
using `first()`

## Properties

### windowId

The native window identifier, or `-1` when unavailable

```kotlin
val desktopWindows = operatingSystem.visibleWindows
val sample: MacOsDesktopWindow = desktopWindows.first()

val windowId: Long = sample.windowId

println(windowId) // e.g. 1234
```

### title

The name of the application owning the window, as reported by the native window list. The implementation reads the owner
name rather than the document or window title, and returns an empty string when unavailable

```kotlin
val desktopWindows = operatingSystem.visibleWindows
val sample: MacOsDesktopWindow = desktopWindows.first()

val title: String = sample.title

println(title) // e.g. Terminal
```

### command

The executable path of the application owning the window, or an empty string when it cannot be resolved

```kotlin
val desktopWindows = operatingSystem.visibleWindows
val sample: MacOsDesktopWindow = desktopWindows.first()

val command: String = sample.command

println(command) // e.g. /System/Applications/Utilities/Terminal.app/Contents/MacOS/Terminal
```

### owningProcessId

The identifier of the process owning the window, or `-1` when unavailable

```kotlin
val desktopWindows = operatingSystem.visibleWindows
val sample: MacOsDesktopWindow = desktopWindows.first()

val owningProcessId: Long = sample.owningProcessId

println(owningProcessId) // e.g. 2345
```

### order

The zero-based position of the window in the queried native list. Its value depends on whether the query used
`visibleWindows` or `allWindows`

```kotlin
val desktopWindows = operatingSystem.visibleWindows
val sample: MacOsDesktopWindow = desktopWindows.first()

val order: Int = sample.order

println(order) // 0 for the first entry
```

### visible

Whether the window is reported as on-screen. A missing native visibility value is treated as `false`

```kotlin
val desktopWindows = operatingSystem.visibleWindows
val sample: MacOsDesktopWindow = desktopWindows.first()

val visible: Boolean = sample.visible

println(visible) // e.g. true
```
