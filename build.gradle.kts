import java.text.SimpleDateFormat
import java.util.Date

plugins {
    id("java")
    id("com.diffplug.spotless") version "7.0.2"
}

group = "com.vitdo82.my"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allprojects {
    apply {
        plugin("java-library")
    }
    version = SimpleDateFormat("yyyymmdd.hhmm").format(Date())
    java {
        toolchain {languageVersion = JavaLanguageVersion.of(21)}
        withSourcesJar()
    }
//    dependencies {
//        implementation("org.springframework.boot:spring-boot-dependencies:3.4.2")
//        implementation("com.google.googlejavaformat:google-java-format:1.20.0")
//    }
    tasks.named<Test>("test") {
        useJUnitPlatform()
    }
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("com.diffplug.spotless")
        plugin("maven-publish")
    }

//    spotless {
//        configure<com.diffplug.gradle.spotless.SpotlessExtension> {
//            configure<com.diffplug.gradle.spotless.SpotlessExtension> {
//                kotlin {
//                    target("**/*.kt")
//                    targetExclude("$buildDir/**/*.kt")
//                    ktlint()
//                    licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
//                }
//                kotlinGradle {
//                    target("*.gradle.kts")
//                    ktlint()
//                }
//                java {
//                    importOrder()
//                    cleanthat()
//                    googleJavaFormat("1.23.0").aosp().reflowLongStrings().formatJavadoc(false).reorderImports(false)
//                        .groupArtifact("com.google.googlejavaformat:google-java-format")
//                    formatAnnotations()
//                }
//            }
//        }
//    }
}
