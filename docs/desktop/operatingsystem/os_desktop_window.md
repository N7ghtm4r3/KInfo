Represents a desktop window on the operating system, providing details about the window's properties, such as its ID, title, associated command,
owning process ID, order in the stack, and visibility

## Properties

### windowId

The unique identifier for the window

```kotlin
val desktopWindows: List<OSDesktopWindow> = operatingSystem.getOSDesktopWindows(
    visibleOnly = // true or false
)
val sample: OSDesktopWindow = desktopWindows.first()

val windowId: Long = sample.windowId

println(windowId) // e.g. 102345
```

### title

The title of the window

```kotlin
val desktopWindows: List<OSDesktopWindow> = operatingSystem.getOSDesktopWindows(
    visibleOnly = // true or false
)
val sample: OSDesktopWindow = desktopWindows.first()

val title: String = sample.title

println(title) // e.g. MyApp
```

### command

The command or executable associated with the window

```kotlin
val desktopWindows: List<OSDesktopWindow> = operatingSystem.getOSDesktopWindows(
    visibleOnly = // true or false
)
val sample: OSDesktopWindow = desktopWindows.first()

val command: String = sample.command

println(command) // e.g. /usr/bin/code
```

### owningProcessId

The process ID of the application owning the window

```kotlin
val desktopWindows: List<OSDesktopWindow> = operatingSystem.getOSDesktopWindows(
    visibleOnly = // true or false
)
val sample: OSDesktopWindow = desktopWindows.first()

val owningProcessId: Long = sample.owningProcessId

println(owningProcessId) // e.g. 2345
```

### order

The position of the window in the stacking order (e.g., topmost window)

```kotlin
val desktopWindows: List<OSDesktopWindow> = operatingSystem.getOSDesktopWindows(
    visibleOnly = // true or false
)
val sample: OSDesktopWindow = desktopWindows.first()

val order: Int = sample.order

println(order) // e.g. 1
```

### visible

A boolean indicating whether the window is currently visible

```kotlin
val desktopWindows: List<OSDesktopWindow> = operatingSystem.getOSDesktopWindows(
    visibleOnly = // true or false
)
val sample: OSDesktopWindow = desktopWindows.first()

val visible: Boolean = sample.visible

println(visible) // true or false
```