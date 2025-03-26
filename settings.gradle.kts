plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "ktor-base"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":dagger-ext")
include(":exposed-ext")
include(":exposed-historical")
include(":jvm-ext")
include(":kt-coroutines-ext")
include(":ktor-core-api")
include(":postgre-ext")
