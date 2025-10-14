## Composable invocation

In `composable` contexts you can use the dedicated `OnPlatform` method to directly retrieve the device information in the 
`commonMain`, avoiding the need to create a dedicated `expect/actual` mechanism.
Below is an example of its usage:

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

## Non-composable invocation

Similar to composable contexts, you can use the dedicated `onPlatform` method outside composable contexts, where composition 
is not needed, to directly retrieve device information in `commonMain`, avoiding the need to create a dedicated `expect/actual` mechanism.
Below is an example of its usage:

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