import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    // TODO: TO USE THIS
    // alias(libs.plugins.androidKotlinMultiplatformLibrary)

    // TODO: INSTEAD OF LEGACY ONE
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.dokka)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "com.teknobit.kinfo"
version = "1.0.6"

kotlin {
// TODO: TO USE THIS
//    androidLibrary {
//        compileSdk = libs.versions.android.compileSdk.get().toInt()
//        minSdk = libs.versions.android.minSdk.get().toInt()
//        namespace = "com.tecknobit.kinfo"
//        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
//
//        compilations {
//            compilerOptions {
//                jvmTarget.set(JvmTarget.JVM_18)
//            }
//        }
//
//    }

// TODO: INSTEAD OF LEGACY ONE
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_18)
        }
    }

    jvm {
        compilations.all {
            this@jvm.compilerOptions {
                jvmTarget.set(JvmTarget.JVM_18)
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "kinfo"
            isStatic = true
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        binaries.library()
        browser {
            webpackTask {

            }
        }
    }

    js {
        browser()
        binaries.library()
    }

    sourceSets {
        applyDefaultHierarchyTemplate()

        // TODO: TO USE THIS
        // val commonMain by getting {
            // dependencies{

        // TODO: INSTEAD OF LEGACY ONE
        androidMain.dependencies {
            implementation(libs.startup.runtime)
            implementation(libs.androidx.core)
            implementation(libs.equinox.compose)
            implementation(libs.equinox.core)
        }

        val commonMain by getting {
            dependencies{
                implementation(libs.compose.ui)
                implementation(libs.compose.runtime)
                implementation(libs.androidx.annotation)
            }
        }

        val iosMain by getting {
            dependencies {
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.oshi.core)
            }
        }

        val webMain by getting {
            dependencies {
                implementation(libs.kotlin.browser)
                implementation(npm("ua-parser-js", "2.0.9"))
            }
        }

        val jsMain by getting {
            dependencies {
            }
        }

        val wasmJsMain by getting {
            dependencies {
            }
        }

    }
    jvmToolchain(18)
}

mavenPublishing {
    configure(
        KotlinMultiplatform(
            javadocJar = JavadocJar.Dokka("dokkaGenerate"),
            androidVariantsToPublish = listOf("release"),
        )
    )
    coordinates(
        groupId = "io.github.n7ghtm4r3",
        artifactId = "kinfo",
        version = "1.0.6"
    )
    pom {
        name.set("KInfo")
        description.set("KInfo is Compose Multiplatform Library allows to access the device details of android, ios, desktop e web devices")
        inceptionYear.set("2026")
        url.set("https://github.com/N7ghtm4r3/KInfo")

        licenses {
            license {
                name.set("Apache License, Version 2.0")
                url.set("https://opensource.org/license/apache-2-0")
            }
        }
        developers {
            developer {
                id.set("N7ghtm4r3")
                name.set("Manuel Maurizio")
                email.set("maurizio.manuel2003@gmail.com")
                url.set("https://github.com/N7ghtm4r3")
            }
        }
        scm {
            url.set("https://github.com/N7ghtm4r3/KInfo")
        }
    }
    publishToMavenCentral()
    signAllPublications()
}

// TODO: REMOVE THIS OF LEGACY ONE
android {
    namespace = "com.tecknobit.kinfo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    buildFeatures {
        buildConfig = true
    }
}