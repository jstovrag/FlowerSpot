package com.flower.spot

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.dependencies

class CommonPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.configurePlugins()
        project.configureAndroid()
        project.configureDependencies()
    }
}

private fun Project.configurePlugins() {
    plugins.apply {
        apply(Plugins.kotlinAndroid)
        apply(Plugins.kotlinKapt)
        apply(Plugins.hilt)
        apply(Plugins.ktLint)
        apply(Plugins.navArgs)
    }
}

private fun Project.configureAndroid() {
    val androidExtensions = extensions.getByName("android")
    if (androidExtensions is BaseExtension) {
        androidExtensions.apply {
            compileSdkVersion(30)
            buildToolsVersion = "30.0.3"

            defaultConfig {
                if (this is AppExtension)
                    applicationId = "com.flower.spot"
                minSdk = 21
                targetSdk = 30
                versionCode = 1
                versionName = "1.0"
            }

            packagingOptions {
                excludes.add("META-INF/licenses/**")
                excludes.add("META-INF/{AL2.0,LGPL2.1}")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            composeOptions {
                kotlinCompilerExtensionVersion = Versions.compose
            }

            buildFeatures.apply {
                compose = true
            }

            when (this) {
                is LibraryExtension -> {
                    defaultConfig {
                        // apply the pro guard rules for library
                        consumerProguardFiles("consumer-rules.pro")
                    }
                }

                is AppExtension -> {
                    buildTypes {
                        getByName("release") {
                            isMinifyEnabled = true
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                        }
                        getByName("debug") {
                            debuggable(true)
                            isMinifyEnabled = false
                        }
                    }
                }
            }
        }
    }
}

private fun Project.configureDependencies() {
    dependencies {
        implementation(Libraries.kotlin_stdlib)
        implementation(Libraries.kotlin_reflect)

        implementation(Libraries.hilt)
        kapt(Libraries.hiltAndroidKapt)

        implementation(Libraries.composeRuntime)
        debugImplementation(Libraries.composeTooling)
        implementation(Libraries.composeActivity)
    }
}

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.kaptAndroidTest(dependencyNotation: Any): Dependency? =
    add("kaptAndroidTest", dependencyNotation)
