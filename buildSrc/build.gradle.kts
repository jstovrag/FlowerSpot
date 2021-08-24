plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    maven(url = "https://plugins.gradle.org/m2/")
}

gradlePlugin {
    plugins {
        register("common-plugin") {
            id = "common-plugin"
            implementationClass = "com.flower.spot.CommonPlugin"
        }
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
}
