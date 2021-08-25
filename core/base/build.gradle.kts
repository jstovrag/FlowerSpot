plugins {
    id(Plugins.library)
    id(Plugins.commonPlugin)
}

dependencies {
    implementation(project(Modules.themeModule))

    implementation(Libraries.navigationCompose)
    api(Libraries.appcompat)
}
