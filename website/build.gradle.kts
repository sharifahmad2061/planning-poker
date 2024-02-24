plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.22"
    id("io.ktor.plugin") version "2.3.8"
    application
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

group = "me.sahmad"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:2.3.8")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.8")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.8")
    implementation("io.ktor:ktor-server-websockets-jvm:2.3.8")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.8")
    implementation("io.ktor:ktor-server-call-logging-jvm:2.3.8")
    implementation("io.ktor:ktor-server-request-validation:2.3.8")
    implementation("io.ktor:ktor-server-html-builder:2.3.8")
    implementation("io.ktor:ktor-server-sessions:2.3.8")

    implementation("ch.qos.logback:logback-classic:1.5.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}