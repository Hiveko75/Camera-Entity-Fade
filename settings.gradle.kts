pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.kikugie.dev/releases")
        maven("https://maven.kikugie.dev/snapshots")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases/")
    }
}

rootDir.resolve(".env").takeIf { it.exists() }?.readLines()?.forEach { line ->
    val trimmed = line.trim()
    if (trimmed.isNotEmpty() && !trimmed.startsWith("#")) {
        val separator = trimmed.indexOf('=')
        if (separator > 0) {
            val key = trimmed.substring(0, separator).trim()
            val value =
                trimmed.substring(separator + 1).trim().removeSurrounding("\"").removeSurrounding("'")
            if (System.getenv(key) == null) System.setProperty(key, value)
        }
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9.7"
}

stonecutter {
    centralScript = "build.gradle.kts"
    kotlinController = true
    shared {
        fun mc(version: String, vararg loaders: String) {
            for (loader in loaders) version("$version-$loader", version)
        }

        mc("1.20.1", "fabric", "forge")
        mc("1.20.4", "fabric", "neoforge")
        mc("1.21", "fabric", "neoforge")
        mc("1.21.11", "fabric", "neoforge")
        mc("26.1", "fabric", "neoforge")
        mc("26.2", "fabric", "neoforge")

        vcsVersion = "1.21.11-fabric"
    }
    create(rootProject)
}

rootProject.name = "camera_entity_fade"
