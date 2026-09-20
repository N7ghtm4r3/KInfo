Represents a printer and provides details about name, driver name, description, current status, and connection
information

## Properties

### name

The name of the printer

```kotlin
val printers = hardware.printers
val sample: Printer = printers.first()

val name: String = sample.name

println(name) // e.g. HP LaserJet Pro M404dn
```

### driverName

The driver name or make/model of the printer

```kotlin
val printers = hardware.printers
val sample: Printer = printers.first()

val driverName: String = sample.driverName

println(driverName) // e.g. HP LaserJet PCL 6
```

### description

The user-friendly description of the printer

```kotlin
val printers = hardware.printers
val sample: Printer = printers.first()

val description: String = sample.description

println(description) // e.g. Office monochrome laser printer located in the administrative department
```

### status

The current status of the printer

```kotlin
val printers = hardware.printers
val sample: Printer = printers.first()

val status: PrinterStatus = sample.status

println(status) // e.g. PRINTING
```

### statusReason

The reason for the current printer status, if available

```kotlin
val printers = hardware.printers
val sample: Printer = printers.first()

val statusReason: String = sample.statusReason

println(statusReason) // e.g. Paper Jam
```

### isDefault

Indicates whether this is the default printer

```kotlin
val printers = hardware.printers
val sample: Printer = printers.first()

val isDefault: Boolean = sample.isDefault

println(isDefault) // true or false
```

### isLocal

Indicates whether this is a local printer

```kotlin
val printers = hardware.printers
val sample: Printer = printers.first()

val isLocal: Boolean = sample.isLocal

println(isLocal) // true or false
```

### portName

The port name or device URI of the printer

```kotlin
val printers = hardware.printers
val sample: Printer = printers.first()

val portName: String = sample.portName

println(portName) // e.g. USB001 (Virtual printer port for USB connection)
```