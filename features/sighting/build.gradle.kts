plugins {
    id(Plugins.library)
    id(Plugins.commonPlugin)
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.flower.sighting.TestAppJUnitRunner"
    }
}

dependencies {
    api(project(Modules.baseModule))
    api(project(Modules.remoteModule))

    androidTestImplementation(Libraries.composeJunit)
    androidTestImplementation(Libraries.hiltAndroidTest)
    androidTestImplementation(Libraries.espresso)
}
