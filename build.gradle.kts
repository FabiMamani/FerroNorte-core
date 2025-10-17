plugins {
    java
    application
    `maven-publish`
}

group = "com.alulescano"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
    implementation("com.google.guava:guava:30.1.1-jre")
    // https://mvnrepository.com/artifact/tools.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-core:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.17.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")


}

application {
    mainClass.set("core.App")
}

tasks.test {
    useJUnitPlatform()
}

// Configuración para publicar en GitHub Packages
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/AluLescano/FerroInc")
            credentials {
                username = (project.findProperty("gpr.user")?.toString()) ?: System.getenv("GITHUB_ACTOR")
                password = (project.findProperty("gpr.key")?.toString()) ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
