import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.dokka)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "com.teknobit.kinfo"
version = "1.0.6"

kotlin {
    androidLibrary {
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        namespace = "com.tecknobit.kinfo"
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true

        compilations {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_18)
            }
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

        val androidMain by getting {
            dependencies {
                implementation(libs.startup.runtime)
                implementation(libs.androidx.core)
                implementation(libs.equinox.compose)
                implementation(libs.equinox.core)
            }
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