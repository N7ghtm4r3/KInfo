Represents the decoded identification information of a macOS display, including its manufacturer, model, physical size, and preferred resolution

The list contains the displays for which the native mapper can retrieve supported identification data. Apple Silicon built-in displays use synthesized EDID data.

The examples use `hardware` from the [Hardware overview](index.md) and assume that the returned list contains at least one item. Check that the list is not empty before calling `first()`.

## Properties

### edid

The Extended Display Identification Data (EDID) of the display. Check `isEdidSynthetic` to distinguish native bytes from data synthesized from display attributes

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val edid: ByteArray = sample.edid

println(edid.contentToString())
```

### isEdidSynthetic

Whether the EDID data was synthesized from the reported display attributes

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val isEdidSynthetic: Boolean = sample.isEdidSynthetic

println(isEdidSynthetic) // e.g. true
```

### manufacturerID

The three-letter manufacturer identifier, or an empty string when the identifier cannot be decoded

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val manufacturerID: String = sample.manufacturerID

println(manufacturerID) // e.g. APP
```

### productID

The product identifier formatted as a hexadecimal string

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val productID: String = sample.productID

println(productID) // e.g. a032
```

### serialNo

The numeric serial bytes formatted as characters or hexadecimal pairs

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val serialNo: String = sample.serialNo

println(serialNo) // e.g. 00000000
```

### week

The manufacture-week byte. The unsigned value `255` is represented as `-1` in this `Byte` property

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val week: Byte = sample.week

println(week) // e.g. 24
```

### year

The year of manufacture. Built-in display information falls back to `1990` when the year is unavailable or outside the supported range

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val year: Int = sample.year

println(year) // e.g. 2023
```

### version

The EDID version in `major.revision` format. Synthesized data uses version `1.4`

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val version: String = sample.version

println(version) // e.g. 1.4
```

### isDigital

Whether the display uses a digital input

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val isDigital: Boolean = sample.isDigital

println(isDigital) // e.g. true
```

### hcm

The horizontal physical size of the display in centimeters, or `0` when unavailable

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val hcm: Int = sample.hcm

println(hcm) // e.g. 30
```

### vcm

The vertical physical size of the display in centimeters, or `0` when unavailable

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val vcm: Int = sample.vcm

println(vcm) // e.g. 20
```

### preferredResolution

The preferred resolution decoded from EDID, or the native resolution reported for a built-in display, in `widthxheight` format. An empty string indicates that the required information is unavailable

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val preferredResolution: String = sample.preferredResolution

println(preferredResolution) // e.g. 3024x1964
```

### model

The model name of the display, or `unknown` when unavailable

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val model: String = sample.model

println(model) // e.g. Color LCD
```

### productSerialNumber

The alphanumeric serial number from the display descriptor or native attributes, or an empty string when unavailable

```kotlin
val displaysInfo: List<MacOsDisplayInfo> = hardware.displaysInfo
val sample: MacOsDisplayInfo = displaysInfo.first()

val productSerialNumber: String = sample.productSerialNumber

println(productSerialNumber) // e.g. EXAMPLE1234
```
