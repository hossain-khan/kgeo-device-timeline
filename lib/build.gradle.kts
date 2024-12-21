plugins {
    kotlin("jvm") version "2.1.0"
    id("org.jetbrains.dokka") version "2.0.0"
}

group = "dev.hossain.timeline"
version = "1.4"

repositories {
    mavenCentral()
}

/**
 * https://github.com/Kotlin/kotlinx.coroutines
 */
val coroutinesVersion = "1.10.1"
val moshiVersion = "1.15.2"
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
    jvmToolchain(19)
}