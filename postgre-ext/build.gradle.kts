plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.drianmr.postgre.ext"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.postgresql)
    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}
