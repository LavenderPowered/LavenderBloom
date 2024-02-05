plugins {
    alias(libs.plugins.blossom)
    alias(libs.plugins.shadowJar)
    java
}

var displayName = "Lavender"

group = "dev.lavenderpowered.lavender"
version = "1.0.0"

dependencies {
    implementation(libs.minestom)
    implementation(libs.minestomext)
    implementation(libs.polar)
    implementation(libs.slf4j)
    implementation(libs.jlineterm)
    implementation(libs.jlineread)
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks {
    blossom {
        replaceToken("&Name", displayName)
        replaceToken("&version", version)
        replaceToken("&commit", libs.versions.minestom.get())
    }

    processResources {
        expand(
            mapOf(
                "Name" to displayName,
                "version" to version
            )
        )
    }

    shadowJar {
        manifest {
            attributes("Main-Class" to "dev.lavenderpowered.lavender.Server")
        }
        archiveBaseName.set(displayName)
        archiveClassifier.set("")
        archiveVersion.set(project.version.toString())
        mergeServiceFiles()
    }

    test {
        useJUnitPlatform()
    }

    build {
        dependsOn(shadowJar)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}