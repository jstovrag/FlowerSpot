plugins {
    id(Plugins.library)
    id(Plugins.commonPlugin)
}

dependencies {
    implementation(project(Modules.baseModule))
    implementation(project(Modules.themeModule))

    implementation(Libraries.navigationCompose)
    implementation(Libraries.hiltNavigation)
}
