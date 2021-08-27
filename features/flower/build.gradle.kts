plugins {
    id(Plugins.library)
    id(Plugins.commonPlugin)
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.flower.flower.TestAppJUnitRunner"
    }
}

dependencies {
    implementation(project(Modules.baseModule))

    implementation(Libraries.hiltNavigation)
    implementation(Libraries.composeCoil)

    testImplementation(Libraries.mockk)
    testImplementation(Libraries.junit)
    testImplementation(Libraries.archCoreTesting)
    testImplementation(Libraries.liveDataTest)

    kaptAndroidTest(Libraries.hiltAndroidKapt)
    debugImplementation(Libraries.composeTestManifest)

    androidTestImplementation(Libraries.mockkAndroid)
    androidTestImplementation(Libraries.espresso)
    androidTestImplementation(Libraries.hiltAndroidTest)
    androidTestImplementation(Libraries.composeJunit)
}
