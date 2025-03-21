group = "com.drianmr.exposed.historical"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(projects.postgreExt)

    implementation(libs.exposed.core)
    implementation(libs.exposed.crypt)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.kotlin.datetime)
    implementation(libs.exposed.json)
    implementation(libs.exposed.money)

    testImplementation(libs.kotlin.test)
}
