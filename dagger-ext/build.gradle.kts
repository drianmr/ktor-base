plugins {
    alias(libs.plugins.ksp)
}

group = "com.drianmr.dagger.ext"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}
