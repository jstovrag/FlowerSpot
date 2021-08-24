plugins {
    id(Plugins.library)
    id(Plugins.commonPlugin)
}

dependencies {
    api(Libraries.coroutinesAndroid)
    api(Libraries.liveData)
    api(Libraries.coroutinesTest)

    api(Libraries.retrofit)

    api(Libraries.moshi)
    kapt(Libraries.moshi_kapt)

    implementation(Libraries.retrofitInterceptor)
    implementation(Libraries.retrofitConvertor)
}
