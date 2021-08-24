plugins {
    id(Plugins.application)
    id(Plugins.commonPlugin)
}

dependencies {
    implementation(project(Modules.baseModule))

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
