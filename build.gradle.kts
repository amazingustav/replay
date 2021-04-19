val kotlinVersion = project.properties["kotlinVersion"]
val micronautVersion = project.properties["micronautVersion"]

plugins {
    val pluginKotlinVersion = "1.4.30"

    kotlin("jvm") version pluginKotlinVersion
    kotlin("kapt") version pluginKotlinVersion
    id("org.jetbrains.kotlin.plugin.allopen") version pluginKotlinVersion
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")

    repositories {
        mavenCentral()
        jcenter()
        gradlePluginPortal()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        maven("https://jcenter.bintray.com")
        maven("https://packages.confluent.io/maven/")
        maven("https://jitpack.io")
    }

    dependencies {
        kapt("io.micronaut:micronaut-bom:$micronautVersion")
        kapt("io.micronaut.data:micronaut-data-processor")
        kapt("io.micronaut:micronaut-validation")
        kapt("io.micronaut:micronaut-inject-java")
    }

    kapt {
        correctErrorTypes = true
    }
}

subprojects {
    group = "br.com.amz"
    version = "0.1"

    repositories {
        mavenCentral()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }

    dependencies {
        kapt("io.micronaut:micronaut-bom:$micronautVersion")
        kapt("io.micronaut.data:micronaut-data-processor")
        kapt("io.micronaut:micronaut-validation")
        kapt("io.micronaut:micronaut-inject-java")

        implementation(platform("io.micronaut:micronaut-bom:$micronautVersion"))

        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.0.2.RELEASE")

        implementation("io.micronaut.reactor:micronaut-reactor")
        implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
        implementation("io.micronaut:micronaut-inject")
        implementation("io.micronaut:micronaut-validation")
        implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")

        implementation("javax.annotation:javax.annotation-api")

        runtimeOnly("ch.qos.logback:logback-classic")
        runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

        // JUnit and Test context
        kaptTest("io.micronaut:micronaut-inject-java")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.3")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")

        testImplementation("io.mockk:mockk:1.11.0")
        testImplementation("io.micronaut.test:micronaut-test-junit5:2.3.4-SNAPSHOT")
    }

    tasks {
        test {
            useJUnitPlatform()
        }

        compileKotlin {
            sourceCompatibility = JavaVersion.VERSION_11.name
            targetCompatibility = JavaVersion.VERSION_11.name

            kotlinOptions {
                freeCompilerArgs = listOf("-Xjvm-default=enable")
                allWarningsAsErrors = true
                jvmTarget = "11"
            }
        }

        compileKotlin {
            kotlinOptions {
                jvmTarget = "11"
            }
        }

        compileTestKotlin {
            kotlinOptions {
                jvmTarget = "11"
            }
        }

        sourceSets {
            getByName("main").java.srcDirs("src/main/kotlin")
        }
    }
}
