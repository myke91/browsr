object Versions {
    val kotlin_version = "1.6.10"
    val appcompat_version = "1.3.1"
    val constraintlayout_version = "2.0.4"
    val core_ktx_version = "1.6.0"
    val lifecycle_version = "2.4.0"
    val navigation_version = "2.3.0"
    val recyclerview_version = "1.2.1"
    val material_version = "1.5.0"
    val retrofit_version = "2.9.0"
    val moshi_version = "1.12.0"
    val dagger_version = "2.40.5"
    val okhttp3_version = "4.2.1"
    val databinding_version = "7.0.4"
    val android_arch_version = "1.1.1"
    val courotine_version = "1.5.2"
    val espresso_version = "3.4.0"
    val cardview_version = "1.0.0"
    val fragment_ktx_version = "1.4.1"
    val picasso_version = "2.71828"
    val jsr250_version = "1.0"
    val junit_version = "4.13.2"
    val mockito_core_version = "3.12.4"
    val core_testing_version = "1.1.1"
    val mockito_inline_version = "2.13.0"
    val kotlinx_courotine_test_version = "1.4.2"
    val test_runner_version = "1.4.1-alpha03"
    val android_test_junit_version = "1.1.3"
    val junit_ktx_version = "1.1.3"
    val gradle_version = "4.2.2"
    val fragment_version = "1.4.1"
}

object Libraries {
    val gradle_build_tools = "com.android.tools.build:gradle:${Versions.gradle_version}"
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    val nagivation_safe_args_plugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation_version}"
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin_version}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout_version}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview_version}"
    val material = "com.google.android.material:material:${Versions.material_version}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview_version}"
    val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx_version}"
    val lifecycle_extensions = "android.arch.lifecycle:extensions:${Versions.android_arch_version}"
    val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    val retrofit_moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit_version}"
    val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3_version}"
    val kotlin_courotines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.courotine_version}"
    val databinding_common = "androidx.databinding:databinding-common:${Versions.databinding_version}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi_version}"
    val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_version}"
    val moshi_adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi_version}"
    val moshi_kotlin_codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi_version}"
    val picasso = "com.squareup.picasso:picasso:${Versions.picasso_version}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger_version}"
    val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger_version}"
    val jsr250 = "javax.annotation:jsr250-api:${Versions.jsr250_version}"
    val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
    val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
    val navigation_dynamic_features =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation_version}"
    val navigation_testing = "androidx.navigation:navigation-testing:${Versions.navigation_version}"
    val junit = "junit:junit:${Versions.junit_version}"
    val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core_version}"
    val core_testing = "android.arch.core:core-testing:${Versions.core_testing_version}"
    val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito_inline_version}"
    val kotlinx_courotines_test =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinx_courotine_test_version}"
    val test_runner = "androidx.test:runner:${Versions.test_runner_version}"
    val android_test_junit = "androidx.test.ext:junit:${Versions.android_test_junit_version}"
    val junit_ktx = "androidx.test.ext:junit-ktx:${Versions.junit_ktx_version}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
    val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso_version}"
    val espresso_idling_resource =
        "androidx.test.espresso:espresso-idling-resource:${Versions.espresso_version}"
    val fragment_testing = "androidx.fragment:fragment-testing:${Versions.fragment_version}"
}

object AppConfig {
    val compileSdkVersion = 31
    val minSdkVersion = 21
    val targetSdkVersion = 31
    val versionCode = 1
    val versionName = "1.0"
}