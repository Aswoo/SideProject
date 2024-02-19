pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SduTest"
include(":app")
include(":feature:main")
include(":feature:bookmarks")
include(":core")
include(":core:ui")
include(":core:designsystem")
include(":core:data")
include(":core:network")
include(":core:model")
include(":core:domain")
include(":core:datastore")
include(":core:common")
include(":core:testing")
