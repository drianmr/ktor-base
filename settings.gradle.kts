plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "ktor-base"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":core-api")
include(":exposed-ext")
include(":exposed-historical")
include(":postgre-ext")
