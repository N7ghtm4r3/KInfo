The device information refers to the details of the device on which the application using **KInfo** is
currently running

## board

The board name of the device hardware

### Original source

The board name is retrieved from `Build.BOARD` property

### KInfo's source

```kotlin
val board: String = androidInfo.board

println(board) // e.g. goldfish_x86_64
```

## bootloader

The version of the device bootloader

### Original source

The version of the bootloader is retrieved from `Build.BOOTLOADER` property

### KInfo's source

```kotlin
val bootloader: String = androidInfo.bootloader

println(bootloader) // e.g. S901EXXU4BWA1
```

## device

The device name

### Original source

The device name is retrieved from `Build.DEVICE` property

### KInfo's source

```kotlin
val device: String = androidInfo.device

println(device) // e.g. emu64xa
```

## display

The display identifier for the build

### Original source

The display identifier is retrieved from `Build.DISPLAY` property

### KInfo's source

```kotlin
val display: String = androidInfo.display

println(display) // e.g. UE1A.230829.036.A4
```

## fingerprint

Unique identifier for the build fingerprint

### Original source

The identifier is retrieved from `Build.FINGERPRINT` property

### KInfo's source

```kotlin
val fingerprint: String = androidInfo.fingerprint

println(fingerprint) 
// e.g. google/sdk_gphone64_x86_64/emu64xa:14/UE1A.230829.036.A4/12096271:user/release-keys
```

## hardware

The name of the device hardware

### Original source

The hardware value is retrieved from `Build.HARDWARE` property

### KInfo's source

```kotlin
val hardware: String = androidInfo.hardware

println(hardware) // e.g. ranchu
```

## host

The host name used to build the system

### Original source

The host name is retrieved from `Build.HOST` property

### KInfo's source

```kotlin
val host: String = androidInfo.host

println(host) // e.g. r-d3d21742fc70d910-x2hk
```

## id

The build ID for the software

### Original source

The build identifier is retrieved from `Build.ID` property

### KInfo's source

```kotlin
val id: String = androidInfo.id

println(id) // e.g. UE1A.230829.036.A4
```

## manufacturer

The manufacturer name of the device

### Original source

The manufacturer value is retrieved from `Build.MANUFACTURER` property

### KInfo's source

```kotlin
val manufacturer: String = androidInfo.manufacturer

println(manufacturer) // e.g. Google
```

## model

The model name of the device

### Original source

The model name is retrieved from `Build.MODEL` property

### KInfo's source

```kotlin
val model: String = androidInfo.model

println(model) // e.g. Pixel 7
```

## brand

The brand name of the device

### Original source

The brand name is retrieved from `Build.BRAND` property

### KInfo's source

```kotlin
val brand: String = androidInfo.brand

println(brand) // e.g. Google
```

## product

The product name of the device

### Original source

The product name is retrieved from `Build.PRODUCT` property

### KInfo's source

```kotlin
val product: String = androidInfo.product

println(product) // e.g. cheetah
```

## deviceOrientation

The current orientation of the device

### Original source

The orientation value is retrieved from `Context.resources.configuration.orientation` property

### KInfo's source

```kotlin
val deviceOrientation: DeviceOrientation = androidInfo.deviceOrientation
```

### Properties

#### isPortrait

Whether the device is currently in portrait mode

```kotlin
val isPortrait: Boolean = deviceOrientation.isPortrait

println(isPortrait) // true or false
```

#### isLandscape

Whether the device is currently in landscape mode

```kotlin
val isLandscape: Boolean = deviceOrientation.isLandscape

println(isLandscape) // true or false
```