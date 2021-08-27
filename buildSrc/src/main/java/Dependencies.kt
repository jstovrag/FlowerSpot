object ClassPath {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val buildTools = "com.android.tools.build:gradle:${Versions.buildTools}"
    const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktLint}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val navArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val kotlinGradle =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradle}"
    const val googleService = "com.google.gms:google-services:${Versions.googleService}"
}

object Plugins {
    const val application = "com.android.application"
    const val library = "com.android.library"

    const val kotlinAndroid = "kotlin-android"
    const val hilt = "dagger.hilt.android.plugin"
    const val navArgs = "androidx.navigation.safeargs.kotlin"

    const val kotlinKapt = "kotlin-kapt"
    const val ktLint = "org.jlleitschuh.gradle.ktlint"

    const val commonPlugin = "common-plugin"

    const val googleService = "com.google.gms.google-services"
}

object Modules {
    const val baseModule = ":core:base"
    const val themeModule = ":core:theme"
    const val remoteModule = ":core:remote"
    const val authenticationModule = ":core:authentication"
    const val homeModule = ":features:home"
    const val flowerModule = ":features:flower"
    const val sightingModule = ":features:sighting"
}

object Libraries {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    const val navigationCompose =
        "androidx.navigation:navigation-compose:${Versions.navigation}"
    const val navigationAnimationCompose =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.navigationComposeAnimation}"
    const val hiltNavigation =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationComposeVersion}"

    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composePager = "com.google.accompanist:accompanist-pager:${Versions.composePager}"
    const val composePagerIndicators =
        "com.google.accompanist:accompanist-pager-indicators:${Versions.composePager}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.composeRuntime}"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    const val composeCoil = "io.coil-kt:coil-compose:${Versions.composeCoil}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAndroidKapt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Versions.hilt}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitInterceptor}"
    const val retrofitConvertor = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshi_kapt = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}"

    const val junit = "junit:junit:${Versions.junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCoreTesting}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val liveDataTest = "com.jraska.livedata:testing-ktx:${Versions.liveDataTest}"
}

object Versions {
    const val kotlin = "1.5.10"
    const val buildTools = "7.0.1"

    const val appcompat = "1.3.1"
    const val navigation = "2.4.0-alpha05"
    const val navigationComposeAnimation = "0.16.0"
    const val hiltNavigationComposeVersion = "1.0.0-alpha03"
    const val kotlinGradle = "1.5.21"
    const val compose = "1.0.0"
    const val composeRuntime = "1.0.1"
    const val composeActivity = "1.3.0"
    const val composePager = "0.14.0"
    const val composeCoil = "1.3.1"

    const val hilt = "2.37"

    const val retrofit = "2.9.0"
    const val moshi = "1.12.0"
    const val retrofitInterceptor = "4.9.1"
    const val coroutines = "1.5.1"
    const val liveData = "2.3.1"

    const val junit = "4.13.2"
    const val espresso = "3.3.0"
    const val archCoreTesting = "2.1.0"
    const val mockk = "1.12.0"
    const val liveDataTest = "1.2.0"

    const val ktLint = "10.0.0"

    const val googleService = "4.3.8"
}
