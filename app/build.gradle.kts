import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")

}


//val keystoreProperties: String = gradleLocalProperties(rootDir + "keystore.properties").getProperty("key")

val keystoreProperties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "keystore.properties")))
}

//val keystorePropertiesFile = rootProject . file ("keystore.properties")
//val keystoreProperties = new Properties()
//keystoreProperties.load(new FileInputStream (keystorePropertiesFile))

android {
    compileSdkVersion(AppConfig.compileSdkVersion)
    defaultConfig {
        applicationId("com.myke.android.browsr")
        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)
        versionCode(AppConfig.versionCode)
        versionName(AppConfig.versionName)
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        buildConfigField("String", "TMDB_API_KEY", keystoreProperties.getProperty("TMDB_API_KEY"))
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "IMAGES_URL", "\"http:/image.tmdb.org/t/p/w500\"")


    }
    buildTypes {
        getByName("debug") {
            minifyEnabled(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":util")))
    implementation(project(mapOf("path" to ":network")))

    implementation(Libraries.kotlin_stdlib)
    implementation(Libraries.appcompat)
    implementation(Libraries.core_ktx)
    implementation(Libraries.constraintlayout)
    implementation(Libraries.recyclerview)
    implementation(Libraries.material)
    implementation(Libraries.cardview)
    implementation(Libraries.fragment_ktx)

    //viewmodel + livedata
    implementation(Libraries.lifecycle_extensions)
    implementation(Libraries.livedata_ktx)
    implementation(Libraries.viewmodel_ktx)

    //retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofit_moshi_converter)
    implementation(Libraries.logging_interceptor)

    //courotines
    implementation(Libraries.kotlin_courotines_android)

    //databinding
    implementation(Libraries.databinding_common)

    //moshi
    implementation(Libraries.moshi)
    implementation(Libraries.moshi_kotlin)
    implementation(Libraries.moshi_adapters)
    kapt(Libraries.moshi_kotlin_codegen)


    //image processing
    implementation(Libraries.picasso)

    //DI (Dagger 2)
    implementation(Libraries.dagger)
    kapt(Libraries.dagger_compiler)
    compileOnly(Libraries.jsr250)


    // Kotlin
    implementation(Libraries.navigation_fragment)
    implementation(Libraries.navigation_ui_ktx)

    // Feature module Support
    implementation(Libraries.navigation_dynamic_features)

    //espresso idling resource
    implementation(Libraries.espresso_idling_resource)

    // Testing Navigation
    androidTestImplementation(Libraries.navigation_testing)


    //test
    testImplementation(Libraries.junit)
    testImplementation(Libraries.mockito_core)
    testImplementation(Libraries.core_testing)
    testImplementation(Libraries.mockito_inline)
    testImplementation(Libraries.kotlinx_courotines_test)

    //android instrumentation
    androidTestImplementation(Libraries.test_runner)
    androidTestImplementation(Libraries.android_test_junit)
    androidTestImplementation(Libraries.junit_ktx)
    androidTestImplementation(Libraries.espresso_core)
    androidTestImplementation(Libraries.espresso_contrib)
    debugImplementation(Libraries.fragment_testing)

}
