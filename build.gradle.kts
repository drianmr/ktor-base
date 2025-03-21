import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.dokka)
    alias(libs.plugins.maven.publish)
}

group = "com.drianmr"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

java {
    withSourcesJar()
}

kotlin {
    jvmToolchain(17)
}

subprojects {
    apply(plugin = rootProject.libs.plugins.kotlin.jvm.get().pluginId)
    apply(plugin = rootProject.libs.plugins.dokka.get().pluginId)
    apply(plugin = rootProject.libs.plugins.maven.publish.get().pluginId)

    val sub = this

    val pomGroup = sub.property("POM_GROUP").toString()
    val pomArtifactId = sub.property("POM_ARTIFACT_ID").toString()
    val pomName = sub.property("POM_NAME").toString()
    val pomDescription = sub.property("POM_DESCRIPTION").toString()
    val pomVersion = sub.property("POM_VERSION").toString()

    mavenPublishing {
        configure(
            KotlinJvm(
                // configures the -javadoc artifact, possible values:
                // - `JavadocJar.None()` don't publish this artifact
                // - `JavadocJar.Empty()` publish an emprt jar
                // - `JavadocJar.Dokka("dokkaHtml")` when using Kotlin with Dokka, where `dokkaHtml` is the name of the Dokka task that should be used as input
                javadocJar = JavadocJar.Dokka("dokkaHtml"),
                // whether to publish a sources jar
                sourcesJar = true,
            )
        )
        // publishToMavenCentral(SonatypeHost.DEFAULT)
        // or when publishing to https://s01.oss.sonatype.org
        // publishToMavenCentral(SonatypeHost.S01)
        // or when publishing to https://central.sonatype.com/
        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

        signAllPublications()

        val propertiesString = sub.ext.properties
            .toSortedMap()
            .map { (k, v) -> "$k=$v" }
            .joinToString(
                prefix = "\n\t├ ",
                separator = ",\n\t├ ",
                // postfix = "\n\t└ ",
            )
        println(
            """Configuring
            |├ group=${sub.group}
            |├ name=${sub.name}
            |├ version=${sub.version}
            |└ POM
            |  ├ GROUP=$pomGroup
            |  ├ ARTIFACT_ID=$pomArtifactId
            |  ├ NAME=$pomName
            |  ├ DESCRIPTION=$pomDescription
            |  └ VERSION=$pomVersion
            |└ extensions=$propertiesString
        """.trimMargin()
        )
        coordinates(sub.group.toString(), sub.name, sub.version.toString())

        pom {
            name.set(sub.ext.properties["pomName"].toString())
            description.set(sub.ext.properties["pomDescription"].toString())
            inceptionYear.set("2025")
            url.set("https://github.com/drianmr/ktor-base/")

            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://opensource.org/license/mit")
                    distribution.set("repo")
                }
            }
            developers {
                developer {
                    id.set("drianmr")
                    name.set("Adrian M. R.")
                    url.set("https://github.com/drianmr/")
                }
            }
            scm {
                url.set("https://github.com/drianmr/ktor-base/")
                connection.set("scm:git:git://github.com/drianmr/ktor-base.git")
                developerConnection.set("scm:git:ssh://git@github.com/drianmr/ktor-base.git")
            }
        }
    }
}
