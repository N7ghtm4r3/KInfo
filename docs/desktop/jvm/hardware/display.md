Represents a display device, such as a monitor or screen, providing details about the 
**Extended Display Identification Data** (**EDID**) for the display. EDID contains information about the display's capabilities and characteristics

## Properties

### edid

The Extended Display Identification Data (EDID) for the display.
It is a byte array that stores the capabilities and characteristics of the display device

```kotlin
val displays = hardware.displays
val sample: Display = displays.first()

val edid: ByteArray = sample.edid

println(edid)
// e.g. [00, FF, FF, FF, FF, FF, FF, 00, 12, 34, 56, 78, 9A, BC, DE, F0, 01, 01, 01, 01, 01, 01, 01, 01]
```