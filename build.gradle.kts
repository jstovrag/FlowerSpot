// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(ClassPath.buildTools)
        classpath(ClassPath.kotlin)
        classpath(ClassPath.hilt)
        classpath(ClassPath.ktLint)
        classpath(ClassPath.navArgs)
        classpath(ClassPath.googleService)
        classpath(ClassPath.kotlinGradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean").configure {
    delete("build")
}
