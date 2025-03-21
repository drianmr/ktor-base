plugins {
    alias(libs.plugins.kotlinx.serialization)
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.drianmr.field.validator)

    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.server.auth)
}
