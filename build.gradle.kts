import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    id ("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "tv.aysu"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(fileTree("lib/"))

    //Discord API
    api("net.dv8tion", "JDA", "5.0.0-alpha.13")

    //Logger
    api("org.slf4j:slf4j-api:1.7.25")
    api("log4j", "log4j", "1.2.17")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}