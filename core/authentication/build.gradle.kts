plugins {
    id(Plugins.library)
    id(Plugins.commonPlugin)
}

dependencies {
    api(project(Modules.baseModule))
    api(project(Modules.remoteModule))
}
