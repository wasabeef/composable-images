plugins {
  id("com.android.application")
  kotlin("android")
  id("kotlin-android-extensions")
  id("kotlin-kapt")
  ktlint
}

android {
  compileSdkVersion(BuildConfig.compileSdk)

  defaultConfig {
    applicationId(BuildConfig.appId)
    minSdkVersion(BuildConfig.minSdk)
    targetSdkVersion(BuildConfig.targetSdk)
    versionCode(BuildConfig.appVersionCode)
    versionName(BuildConfig.appVersionName)

    testInstrumentationRunner = BuildConfig.testRunner
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  buildFeatures {
    compose = true
  }

  lintOptions {
    disable("InvalidFragmentVersionForActivityResult")
  }

  packagingOptions {
    exclude("META-INF/*.kotlin_module")
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

repositories {
  maven(url = "https://dl.bintray.com/wasabeef/maven/")
}

dependencies {
  implementation(project(Projects.glideComposable))
  implementation(project(Projects.picassoComposable))
  implementation(project(Projects.coilComposable))

  implementation(Libraries.kotlin)
  implementation(Libraries.appcompat)
  implementation(Libraries.coreKtx)

  implementation(Libraries.Compose.runtime)
  implementation(Libraries.Compose.foundation)
  implementation(Libraries.Compose.ui)
  implementation(Libraries.Compose.layout)
  implementation(Libraries.Compose.material)
  implementation(Libraries.Compose.tooling)

  implementation(Libraries.Transformers.glide)
  implementation(Libraries.Transformers.glideGpu)
  implementation(Libraries.Transformers.picasso)
  implementation(Libraries.Transformers.picassoGpu)
  implementation(Libraries.Transformers.coil)
  implementation(Libraries.Transformers.coilGpu)
}
