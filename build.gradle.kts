import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

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
//    shadow plugin from johnrengelman/shadow to package the complete application into a single fat-jar.
//    id("com.github.johnrengelman.shadow") version "2.0.4"
}

//apply {
//    plugin(ShadowPlugin::class.java)
//}

//tasks {
//    withType<KotlinCompile> {
//        kotlinOptions {
//            jvmTarget = "1.8"
//            apiVersion = "1.2"
//            languageVersion = "1.2"
//        }
//    }
//
//    withType<ShadowJar> {
//        classifier = "fat"
//        isZip64 = true
//    }
//}

group = "com.yassir.vertx"
version = "1.0-SNAPSHOT"


application{
    mainClassName = "com.yassir.HelloKt"
}

val vertxVersion = "3.5.2"
//val jacksonVersion = "2.9.6"
//val jacksonKotlinModuleVersion = "2.9.4.1"
//val slf4jVersion = "1.7.25"
//val logbackVersion = "1.2.3"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")

    // loging
//    compile(group = "org.slf4j", name = "slf4j-api", version = slf4jVersion)
//    compile(group = "ch.qos.logback", name = "logback-classic", version = logbackVersion)

    // vertx
    compile (group = "io.vertx", name = "vertx-core", version = vertxVersion)
    compile (group = "io.vertx", name = "vertx-web", version = vertxVersion)

    // jackson
//    compile(group = "com.fasterxml.jackson.datatype", name = "jackson-datatype-jsr310", version = jacksonVersion)
//    compile(group = "com.fasterxml.jackson.module", name = "jackson-module-paranamer", version = jacksonVersion)
//    compile(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin", version = jacksonKotlinModuleVersion)

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}