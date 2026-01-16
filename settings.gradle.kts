rootProject.name = "ReadBird"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                @Suppress("UnstableApiUsage")
                includeGroupAndSubgroups("androidx")
                @Suppress("UnstableApiUsage")
                includeGroupAndSubgroups("com.android")
                @Suppress("UnstableApiUsage")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google {
            mavenContent {
                @Suppress("UnstableApiUsage")
                includeGroupAndSubgroups("androidx")
                @Suppress("UnstableApiUsage")
                includeGroupAndSubgroups("com.android")
                @Suppress("UnstableApiUsage")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")
