plugins {
    alias(libs.plugins.kotlinx.serialization)
}

group = "com.drianmr.ktor.core.api"
version = "1.0.2-SNAPSHOT"

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.drianmr.field.validator)

    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.server.auth)
}
