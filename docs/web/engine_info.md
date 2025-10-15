The engine information refers to the details of the engine of the browser where the application is running

### Original source

The engine information are retrieved from `UAParser.result.engine` interface

### KInfo's source

```kotlin
val engine: Engine = webInfo.engine
```

### Properties

#### name

The name of the browser engine

```kotlin
val name: String = engine.name

println(name) //e.g. Blink
```

#### version

The version of the engine

```kotlin
val version: String = engine.version

println(version) //e.g. 91.0
```