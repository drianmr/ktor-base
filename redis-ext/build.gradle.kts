group = "com.drianmr.redis.ext"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.reactive)
    implementation(libs.lettuce.core)

    testImplementation(libs.kotlin.test)
}
