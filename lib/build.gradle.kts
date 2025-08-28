plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
}

group = "dev.hossain.timeline"
version = "1.4"
description = "A Kotlin library for parsing Google Location History Timeline JSON data"

repositories {
    mavenCentral()
}

/**
 * https://github.com/Kotlin/kotlinx.coroutines
 */
val coroutinesVersion = "1.10.1"
val moshiVersion = "1.15.1"
val okioVersion = "3.9.1"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    api("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    api("com.squareup.okio:okio:$okioVersion")

    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}