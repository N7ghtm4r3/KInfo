# Overview

The web platform works under the hood with the [ua-parser-js library](https://github.com/faisalman/ua-parser-js) to retrieve the information,
specially the [UAParser](https://docs.uaparser.dev/api/main/overview.html) information

## Available information

On **web** target are available the below information:

| **Category**     | **Property**   | **Description**                                                        | **Source**                         |
|------------------|----------------|------------------------------------------------------------------------|------------------------------------|
| **Browser Info** | `name`         | The name of the browser                                                | `UAParser.result.browser`          |
|                  | `version`      | The version of the browser                                             | `UAParser.result.browser.version`  |
|                  | `major`        | The major version number of the browser                                | `UAParser.result.browser.major`    |
|                  | `type`         | Type of current browser                                                | `UAParser.result.browser.type`     |
| **OS Info**      | `name`         | The name of the operating system                                       | `UAParser.result.os.name`          |
|                  | `version`      | The version of the operating system                                    | `UAParser.result.os.version`       |
| **Device Info**  | `model`        | The model of the device                                                | `UAParser.result.device.model`     |
|                  | `type`         | The type of the device                                                 | `UAParser.result.device.type`      |
|                  | `vendor`       | The vendor of the device                                               | `UAParser.result.device.vendor`    |
| **CPU Info**     | `architecture` | The CPU architecture of the device                                     | `UAParser.result.cpu.architecture` |
| **Engine Info**  | `name`         | The name of the browser engine (e.g., "Blink", "WebKit")               | `UAParser.result.engine.name`      |
|                  | `version`      | The version of the engine (e.g., "91.0")                               | `UAParser.result.engine.version`   |
| **Web Info**     | `userAgent`    | The user agent string representing the client's web browser and system | `window.navigator.userAgent`       |

## API source

The information are retrievable using the `WebInfo` API:

### Composable context

Retrieve a `KInfoState` instance inside **composable** context

```kotlin
val kInfoState = rememberKInfoState()
```

### Non-composable context

Retrieve a `KInfoState` instance inside **non-composable** context

```kotlin
val kInfoState = KInfoState()
```

### WebInfo

Retrieve a `WebInfo` instance from `kInfoState` instance

```kotlin

val webInfo = kInfoState.webInfo 
```

!!! Warning

    You can directly retrieve `webInfo` just inside the `webMain` module, in the `commonMain` module you have
    to use the [common usage](../usage.md) instead, or the application will crash