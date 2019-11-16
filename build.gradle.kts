import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    mavenLocal()
    mavenCentral()
    gradleKotlinDsl()
    jcenter()
}


plugins {
    java
    application
    kotlin("jvm") version "1.3.21"
}

group = "com.yassir.vertx"
version = "1.0-SNAPSHOT"


application{
    mainClassName = "com.yassir.HelloKt"
}

val vertxVersion = "3.5.2"
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")

    // vertx
    compile (group = "io.vertx", name = "vertx-core", version = vertxVersion)
    compile (group = "io.vertx", name = "vertx-web", version = vertxVersion)

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}