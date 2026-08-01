plugins {
    id("dev.kikugie.stonecutter")

    val modstitchVersion = "0.8.4"
    id("dev.isxander.modstitch.base") version modstitchVersion apply false
    id("net.fabricmc.fabric-loom") version "1.15-SNAPSHOT" apply false
    id("me.modmuss50.mod-publish-plugin") version "0.8.4" apply false
}

stonecutter active "26.2-fabric"

val checkArchitecture by tasks.registering {
    group = "verification"
    description = "Checks that rendering bridges remain independent of policy logic."
    val sourceRoot = layout.projectDirectory.dir("src/main/java")
    inputs.dir(sourceRoot)
    doLast {
        val violations = mutableListOf<String>()
        fileTree(sourceRoot).matching {
            include("io/github/leawind/cameraentityfade/internal/bridge/**/*.java")
        }.files.sorted().forEach { source ->
            source.readLines().forEachIndexed { index, line ->
                if (line.contains("import io.github.leawind.cameraentityfade.internal.logic.")) {
                    violations.add("${source.name}:${index + 1}: bridge must not import logic")
                }
            }
        }
        if (violations.isNotEmpty()) {
            throw GradleException(violations.joinToString("\n", "Architecture violations:\n"))
        }
    }
}

val buildAndCollect by tasks.registering(Sync::class) {
    group = "build"
    description = "Builds and collects all distributable jars."
    dependsOn(checkArchitecture)
    into(layout.buildDirectory.dir("libs"))
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        exclusiveContent {
            forRepository {
                maven("https://maven.gnomecraft.net/releases") {
                    name = "GnomeCraft (Terraformers Mirror)"
                }
            }
            filter { includeGroup("com.terraformersmc") }
        }
        exclusiveContent {
            forRepository { maven("https://maven.isxander.dev/releases") }
            filter { includeGroup("dev.isxander") }
        }
        exclusiveContent {
            forRepository { maven("https://maven.quiltmc.org/repository/release") }
            filter { includeGroup("org.quiltmc.parsers") }
        }
        exclusiveContent {
            forRepository { maven("https://maven.nucleoid.xyz") }
            filter { includeGroupByRegex("eu\\.pb4(\\..*)?") }
        }
        maven("https://maven.neoforged.net/releases/") {
            content { includeGroupByRegex("net\\.neoforged(\\..*)?") }
        }
        maven("https://maven.minecraftforge.net/") {
            content { includeGroupByRegex("net\\.minecraftforge(\\..*)?") }
        }
    }
}
