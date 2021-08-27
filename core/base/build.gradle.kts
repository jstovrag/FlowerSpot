plugins {
    id(Plugins.library)
    id(Plugins.commonPlugin)
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.flower.base.TestAppJUnitRunner"
    }
}

dependencies {
    api(project(Modules.themeModule))
    api(project(Modules.remoteModule))

    implementation(Libraries.navigationCompose)
    api(Libraries.appcompat)

    debugImplementation(Libraries.composeTestManifest)

    androidTestImplementation(Libraries.composeJunit)
    androidTestImplementation(Libraries.hiltAndroidTest)
    androidTestImplementation(Libraries.espresso)
}
