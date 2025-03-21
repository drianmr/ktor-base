plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.drianmr.exposed.ext"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinx.datetime)

    implementation(libs.exposed.core)
    implementation(libs.exposed.crypt)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.kotlin.datetime)
    implementation(libs.exposed.json)
    implementation(libs.exposed.money)

    implementation(libs.postgresql)

    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}
