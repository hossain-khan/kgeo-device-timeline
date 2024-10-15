plugins {
    kotlin("jvm") version "2.0.21"
    id("org.jetbrains.dokka") version "1.9.20"
}

group = "dev.hossain.timeline"
version = "1.1-SNAPSHOT"

repositories {
    mavenCentral()
}

/**
 * https://github.com/Kotlin/kotlinx.coroutines
 */
val coroutinesVersion = "1.9.0"
val moshiVersion = "1.15.1"
val okioVersion = "3.9.1"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation("com.squareup.okio:okio:$okioVersion")

    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}