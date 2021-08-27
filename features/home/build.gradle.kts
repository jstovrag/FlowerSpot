plugins {
    id(Plugins.library)
    id(Plugins.commonPlugin)
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.flower.home.TestAppJUnitRunner"
    }
}

dependencies {
    implementation(project(Modules.baseModule))
    implementation(project(Modules.flowerModule))
    implementation(project(Modules.sightingModule))

    implementation(Libraries.navigationCompose)
    implementation(Libraries.hiltNavigation)
}
