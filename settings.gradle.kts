pluginManagement {
    repositories { google(); mavenCentral(); gradlePluginPortal() }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Artifex repository for MuPDF (AGPL-3.0)
        maven { url = uri("https://maven.ghostscript.com") }
    }
}
rootProject.name = "Orbit8LicenseTestbed"
include(":app")
