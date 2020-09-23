import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    id("io.gitlab.arturbosch.detekt") version "1.13.1"
    application
}
group = "com.memento"

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://dl.bintray.com/kotlin/ktor") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlinx") }
}
dependencies {
    // Ktor
    implementation("io.ktor:ktor-server-netty:1.4.0")
    testImplementation("io.ktor:ktor-server-test-host:1.4.0")
    testImplementation("io.kotest:kotest-assertions-ktor:4.2.5")
    
    // Kotest
    testImplementation("io.kotest:kotest-runner-junit5:4.2.5")
    
    // Detekt
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.13.1")
    
    // Mockk
    testImplementation("io.mockk:mockk:1.10.0")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClassName = "ServerKt"
}

tasks.withType<Test> {
    useJUnitPlatform()
}