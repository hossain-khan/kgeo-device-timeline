plugins {
    kotlin("jvm")
}

group = "dev.hossain.timeline.sample"
version = "1.0-SNAPSHOT"
description = "Sample application demonstrating usage of the kgeo-device-timeline library"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lib"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}