plugins {
  id("com.android.library")
  kotlin("android")
  id("kotlin-android")
  ktlint
}

android {
  compileSdkVersion(BuildConfig.compileSdk)

  defaultConfig {
    minSdkVersion(BuildConfig.minSdk)
    targetSdkVersion(BuildConfig.targetSdk)

    testInstrumentationRunner = BuildConfig.testRunner
    consumerProguardFile("consumer-rules.pro")
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  buildFeatures {
    buildConfig = false
    compose = true
  }

  lintOptions {
    disable("InvalidFragmentVersionForActivityResult")
  }

  composeOptions {
    kotlinCompilerVersion = Libraries.kotlinVersion
    kotlinCompilerExtensionVersion = Libraries.Compose.composeVersion
  }

  tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
    kotlinOptions {
      freeCompilerArgs = freeCompilerArgs + "-Xallow-jvm-ir-dependencies"
      freeCompilerArgs = freeCompilerArgs + "-Xskip-prerelease-check"
    }
  }
}

dependencies {
  implementation(Libraries.kotlin)
  api(Libraries.glide)
  implementation(Libraries.coreKtx)
  implementation(Libraries.annontation)
  implementation(Libraries.Compose.runtime)
  implementation(Libraries.Compose.foundation)
  implementation(Libraries.Compose.ui)
}

extra.apply {
  set("bintrayRepo", "maven")
  set("bintrayName", "composable-images")
  set("bintrayUserOrg", "wasabeef")
  set("publishedGroupId", "jp.wasabeef.composable")
  set("libraryName", "composable-images")
  set("artifact", "glide")
  set("libraryDescription", "A simple library for loading into Jetpack Compose Image using Glide.")
  set("siteUrl", "https://github.com//wasabeef/transformers")
  set("gitUrl", "https://github.com//wasabeef/transformers.git")
  set("issueUrl", "https://github.com//wasabeef/transformers/issues")
  set("libraryVersion", BuildConfig.appVersionName)
  set("developerId", "wasabeef")
  set("developerName", "Daichi Furiya")
  set("developerEmail", "dadadada.chop@gmail.com")
  set("licenseName", "The Apache Software License, Version 2.0")
  set("licenseUrl", "http://www.apache.org/licenses/LICENSE-2.0.txt")
  set("allLicenses", arrayOf("Apache-2.0"))
}

apply(from = "https://gist.githubusercontent.com/wasabeef/cf14805bee509baf7461974582f17d26/raw/bintray-v1.gradle")
apply(from = "https://gist.githubusercontent.com/wasabeef/cf14805bee509baf7461974582f17d26/raw/install-v1.gradle")