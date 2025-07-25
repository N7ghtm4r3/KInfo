# KInfo

![Maven Central](https://img.shields.io/maven-central/v/io.github.n7ghtm4r3/KInfo.svg?label=Maven%20Central)

![Static Badge](https://img.shields.io/badge/android-4280511051)
![Static Badge](https://img.shields.io/badge/ios-445E91)
![Static Badge](https://img.shields.io/badge/desktop-006874)
![Static Badge](https://img.shields.io/badge/wasmjs-834C74)

**v1.0.3**

KInfo is Compose Multiplatform Library allows to access the device details of android, ios, desktop e web devices 

## Integration

### Implementation

#### Version catalog

```gradle
[versions]
kinfo = "1.0.3"

[libraries]
kinfo = { module = "io.github.n7ghtm4r3:KInfo", version.ref = "kinfo" } 
```

#### Gradle

Add the JitPack repository to your build file

- Add it in your root build.gradle at the end of repositories

    ```gradle
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
    ```

  #### Gradle (Kotlin)

    ```gradle
    repositories {
        ...
        maven("https://jitpack.io")
    }
    ```

- Add the dependency

    ```gradle
    dependencies {
        implementation 'io.github.n7ghtm4r3:KInfo:1.0.3'
    }
    ```

  #### Gradle (Kotlin)

    ```gradle
    dependencies {
        implementation("io.github.n7ghtm4r3:KInfo:1.0.3")
    }
    ```

  #### Gradle (version catalog)

    ```gradle
    dependencies {
        implementation(libs.kinfo)
    }
    ```
  
## Usage

### Composable invocation

```kotlin
OnPlatform(
    onAndroid = { androidInfo ->
        // uses the Android information retrieved
        Text(
            text = androidInfo.model
        )
    },
    onIos = { iosInfo ->
        // uses the iOs information retrieved
        Text(
            text = iosInfo.model
        )
    },
    onDesktop = { desktopInfo ->
        // uses the desktop information retrieved
        Text(
            text = desktopInfo.hardware.computerSystem.model // hardware information
        )
        Text(
            text = desktopInfo.operatingSystem.versionInfo.codeName // operating system information
        )
    },
    onWeb = { webInfo: WebInfo ->
        // uses the web information retrieved
        Text(
            text = webInfo.browser.name
        )
    }
)
```

### Non-composable invocation

```kotlin
onPlatform(
    onAndroid = { androidInfo ->
        // uses the Android information retrieved
        println(androidInfo.model)
    },
    onIos = { iosInfo ->
        // uses the iOs information retrieved
        println(iosInfo.model)
    },
    onDesktop = { desktopInfo ->
        // uses the desktop information retrieved
        println(desktopInfo.hardware.computerSystem.model) // hardware information
        println(desktopInfo.operatingSystem.versionInfo.codeName) // operating system information
    },
    onWeb = { webInfo: WebInfo ->
        // uses the web information retrieved
        println(webInfo.browser.name)
    }
)
```

## Information available

Take a look to related documentation about the information available for each platform

- [Android](documd/AndroidInfo.md)
- [Ios](documd/IosInfo.md)
- [Desktop](documd/DesktopInfo.md)
- [Web](documd/WebInfo.md)

Will be gradually added new information for each platform 

## Support

If you need help using the library or encounter any problems or bugs, please contact us via the
following links:

- Support via <a href="mailto:infotecknobitcompany@gmail.com">email</a>
- Support via <a href="https://github.com/N7ghtm4r3/KInfo/issues/new">GitHub</a>

Thank you for your help!

## Donations

If you want support project and developer

| Crypto                                                                                              | Address                                          | Network  |
|-----------------------------------------------------------------------------------------------------|--------------------------------------------------|----------|
| ![](https://img.shields.io/badge/Bitcoin-000000?style=for-the-badge&logo=bitcoin&logoColor=white)   | **3H3jyCzcRmnxroHthuXh22GXXSmizin2yp**           | Bitcoin  |
| ![](https://img.shields.io/badge/Ethereum-3C3C3D?style=for-the-badge&logo=Ethereum&logoColor=white) | **0x1b45bc41efeb3ed655b078f95086f25fc83345c4**   | Ethereum |
| ![](https://img.shields.io/badge/Solana-000?style=for-the-badge&logo=Solana&logoColor=9945FF)       | **AtPjUnxYFHw3a6Si9HinQtyPTqsdbfdKX3dJ1xiDjbrL** | Solana   |

If you want support project and developer
with <a href="https://www.paypal.com/donate/?hosted_button_id=5QMN5UQH7LDT4">PayPal</a>

# Licence

```
MIT License

Copyright (c) 2024 Swapnil Musale

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

Copyright © 2025 Tecknobit
