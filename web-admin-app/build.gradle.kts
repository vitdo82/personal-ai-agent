import com.github.gradle.node.npm.task.NpmTask

plugins {
    java
    id("com.github.node-gradle.node") version "7.1.0"
}

group = "com.vitdo82.personal-ai-agent"
version = "0.0.1-SNAPSHOT"

node {
    version = "22.11.0"
    npmVersion = "10.9.0"
    download = true
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

tasks.register<NpmTask>("installFrontendDependencies") {
    group = "build"
    description = "Install frontend dependencies using npm"
    workingDir.set(file("./"))
    args.set(listOf("install"))
}

tasks.register<NpmTask>("buildAndIncludeFrontend") {
    dependsOn("installFrontendDependencies")
    group = "build"
    description = "Build frontend using npm and include it in the main build"
    workingDir = file("./")
    args.set(listOf("run", "build"))
}

tasks.named("processResources") {
    dependsOn("buildAndIncludeFrontend")
}

tasks.register<NpmTask>("webRun") {
    dependsOn("installFrontendDependencies")
    group = "application"
    description = "Run frontend"
    workingDir = file("./")
    args.set(listOf("run", "dev"))
}
