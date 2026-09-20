Represents a macOS printing destination, providing its name, model, status, and connection information

The list contains the destinations returned by CUPS. The status reflects the reported queue state and reasons.

The examples use `hardware` from the [Hardware overview](index.md) and assume that the returned list contains at least one item. Check that the list is not empty before calling `first()`.

## Properties

### name

The name of the printer, including a `/instance` suffix when a destination instance is present

```kotlin
val printers: List<MacOsPrinter> = hardware.printers
val sample: MacOsPrinter = printers.first()

val name: String = sample.name

println(name) // e.g. Office_Printer
```

### driverName

The reported make and model of the printer, or `unknown` when unavailable

```kotlin
val printers: List<MacOsPrinter> = hardware.printers
val sample: MacOsPrinter = printers.first()

val driverName: String = sample.driverName

println(driverName) // e.g. Generic PostScript Printer
```

### description

The user-friendly description of the printer, falling back to the destination name when unavailable

```kotlin
val printers: List<MacOsPrinter> = hardware.printers
val sample: MacOsPrinter = printers.first()

val description: String = sample.description

println(description) // e.g. Office printer
```

### status

The current printer status: `IDLE`, `PRINTING`, `ERROR`, `OFFLINE`, or `UNKNOWN`

```kotlin
val printers: List<MacOsPrinter> = hardware.printers
val sample: MacOsPrinter = printers.first()

val status: PrinterStatus = sample.status

println(status) // e.g. IDLE
```

### statusReason

The comma-separated CUPS reason keywords for the current status, or `unknown` when unavailable

```kotlin
val printers: List<MacOsPrinter> = hardware.printers
val sample: MacOsPrinter = printers.first()

val statusReason: String = sample.statusReason

println(statusReason) // e.g. none
```

### isDefault

Whether the printer is marked as the default destination

```kotlin
val printers: List<MacOsPrinter> = hardware.printers
val sample: MacOsPrinter = printers.first()

val isDefault: Boolean = sample.isDefault

println(isDefault) // e.g. true
```

### isLocal

Whether the device URI uses a USB, parallel, or serial connection scheme. Unavailable or unrecognized schemes return `false`

```kotlin
val printers: List<MacOsPrinter> = hardware.printers
val sample: MacOsPrinter = printers.first()

val isLocal: Boolean = sample.isLocal

println(isLocal) // e.g. false
```

### portName

The device URI used to address the printer, or `unknown` when unavailable

```kotlin
val printers: List<MacOsPrinter> = hardware.printers
val sample: MacOsPrinter = printers.first()

val portName: String = sample.portName

println(portName) // e.g. ipp://192.0.2.20/ipp/print
```
