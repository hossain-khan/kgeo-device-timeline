plugins {
    kotlin("jvm")
}

group = "dev.hossain.timeline"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    implementation("com.squareup.okio:okio:3.4.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}