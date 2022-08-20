import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    id ("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "tv.aysu"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    //Discord API
    implementation("net.dv8tion", "JDA", "5.0.0-alpha.13")

    //Logger
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("log4j", "log4j", "1.2.17")

    implementation("me.carleslc.Simple-YAML", "Simple-Yaml", "1.7.2")

    api("org.jetbrains:annotations:23.0.0")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}