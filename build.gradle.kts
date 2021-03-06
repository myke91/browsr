// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath(Libraries.gradle_build_tools)
        classpath(Libraries.kotlin_gradle_plugin)
        classpath(Libraries.nagivation_safe_args_plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()

    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
