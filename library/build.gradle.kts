
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.dokka.DokkaConfiguration.Visibility.*
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.dokka)
}

group = "com.teknobit.kinfo"
version = "1.0.2"

kotlin {
    jvm {
        compilations.all {
            this@jvm.compilerOptions {
                jvmTarget.set(JvmTarget.JVM_18)
            }
        }
    }

    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_18)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "KInfo"
            isStatic = true
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        binaries.executable()
        browser {
            webpackTask {
                dependencies {
                    implementation(libs.x.cdn.jsdelivr.net.npm.ua.parser.js)
                }
            }
        }
    }

    sourceSets {

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

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.oshi.core)
            }
        }

    }
    jvmToolchain(18)
}

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

mavenPublishing {
    configure(
        KotlinMultiplatform(
            javadocJar = JavadocJar.Dokka("dokkaHtml"),
            sourcesJar = true,
            androidVariantsToPublish = listOf("release"),
        )
    )
    coordinates(
        groupId = "io.github.n7ghtm4r3",
        artifactId = "KInfo",
        version = "1.0.2"
    )
    pom {
        name.set("KInfo")
        description.set("KInfo is Compose Multiplatform Library allows to access the device details of android, ios, desktop e web devices")
        inceptionYear.set("2025")
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
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}

buildscript {
    dependencies {
        classpath(libs.dokka.base)
    }
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}

tasks.dokkaHtml {
    outputDirectory.set(layout.projectDirectory.dir("../docs"))
    dokkaSourceSets.configureEach {
        moduleName = "KInfo"
        includeNonPublic.set(true)
        documentedVisibilities.set(setOf(PUBLIC, PROTECTED, PRIVATE, INTERNAL))
    }
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        footerMessage = "(c) 2025 Tecknobit"
    }
}

configurations.all {
    exclude("io.github.n7ghtm4r3", "Equinox-Compose-android")
}
