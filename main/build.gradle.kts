plugins {
    application
    id("io.micronaut.application") version "1.4.2"
}

kapt {
    arguments {
        arg("micronaut.processing.incremental", true)
        arg("micronaut.processing.annotations", "br.com.amz.*,io.amz.*")
    }
}

application {
    mainClass.set("br.com.amz.replay.Application")

    applicationDefaultJvmArgs = listOf(
        "-server",
        "-XX:+UseNUMA",
        "-XX:+UseParallelGC",
        "-Duser.timezone=America/Sao_Paulo"
    )
}

micronaut {
    version("2.4.2-SNAPSHOT")
    runtime("netty")
    testRuntime("kotest")

    processing {
        incremental(true)
        annotations("br.com.amz.*")
    }
}

tasks {
    jar {
        archiveBaseName.set("replay-micronaut")
        archiveVersion.set("")

        manifest {
            attributes(mapOf("Main-Class" to application.mainClass))
        }

        from(
            Callable {
                duplicatesStrategy = DuplicatesStrategy.INCLUDE
                isZip64 = true
                configurations["runtimeClasspath"].map { if (it.isDirectory) it else zipTree(it) }
            }
        )
    }
}

val micronautVersion = project.properties["micronautVersion"]

val inputProjects = listOf(":rest")
val outputProjects = listOf(":mysql")
val projects = listOf(":domain") + inputProjects + outputProjects

dependencies {
    projects.map { project(it) }.forEach { implementation(it) }

    kapt("io.micronaut:micronaut-bom:${micronautVersion}")
    implementation(platform("io.micronaut:micronaut-bom:${micronautVersion}"))

    implementation("io.micronaut:micronaut-runtime")

    kaptTest("io.micronaut:micronaut-bom:${micronautVersion}")
}
