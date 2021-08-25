plugins {
    id(Plugins.application)
    id(Plugins.commonPlugin)
}

dependencies {
    implementation(project(Modules.baseModule))
    implementation(project(Modules.homeModule))
    implementation(project(Modules.themeModule))
    implementation(project(Modules.remoteModule))

    implementation(Libraries.composePager)
    implementation(Libraries.composePagerIndicators)
}

// a workaround to be able to run release version without signingConfigs
// TODO: it should be replaced with the right one
android {
    buildTypes {
        release {
            signingConfig = signingConfigs.findByName("debug")
        }
    }
}
