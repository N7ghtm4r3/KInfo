plugins {
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    // TODO: TO USE THIS
    // alias(libs.plugins.androidKotlinMultiplatformLibrary) apply false

    // TODO: INSTEAD OF LEGACY ONE
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.dokka) apply false
    alias(libs.plugins.vanniktech.mavenPublish) apply false
}