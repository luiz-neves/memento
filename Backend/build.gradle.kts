import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.3.72"
    id("io.gitlab.arturbosch.detekt") version "1.13.1"
    application
}

group = "com.memento"

repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlinx") }
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    // Hibernate
    implementation("org.hibernate.validator:hibernate-validator")

    // PostgreSQL
    runtimeOnly("org.postgresql:postgresql")

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
    mainClassName = "com.memento.ApplicationKt"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

detekt {
    autoCorrect = true
}