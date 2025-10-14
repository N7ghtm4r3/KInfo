## Composable invocation

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