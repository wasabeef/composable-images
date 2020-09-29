object BuildConfig {
  const val compileSdk = 30

  const val appId = "jp.wasabeef.composable"
  const val minSdk = 21
  const val targetSdk = 30
  const val appVersionCode = 101
  const val appVersionName = "1.0.1"

  const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Projects {
  const val glideComposable = ":composable:glide"
  const val picassoComposable = ":composable:picasso"
  const val coilComposable = ":composable:coil"
}

object Ktlint {
  const val plugin = "com.pinterest:ktlint:0.39.0"
}

object Libraries {
  const val kotlinVersion = "1.4.10"
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

  private const val appcompatVersion = "1.2.0"
  private const val coreKtxVersion = "1.2.0"
  const val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
  const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
  const val annontation = "androidx.annotation:annotation:1.2.0-alpha01"

  object Compose {
    const val composeVersion = "1.0.0-alpha03"
    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val foundation = "androidx.compose.foundation:foundation:${composeVersion}"
    const val layout = "androidx.compose.foundation:foundation-layout:${composeVersion}"
    const val ui = "androidx.compose.ui:ui:${composeVersion}"
    const val material = "androidx.compose.material:material:${composeVersion}"
    const val tooling = "androidx.ui:ui-tooling:${composeVersion}"
  }

  const val picasso = "com.squareup.picasso:picasso:2.8"
  const val glide = "com.github.bumptech.glide:glide:4.11.0"
  const val coil = "io.coil-kt:coil:1.0.0-rc3"
  const val coilGif = "io.coil-kt:coil-gif:1.0.0-rc3"

  object Transformers {
    const val glide = "jp.wasabeef.transformers:glide:0.0.2"
    const val glideGpu = "jp.wasabeef.transformers:glide-gpu:0.0.2"
    const val picasso = "jp.wasabeef.transformers:picasso:0.0.2"
    const val picassoGpu = "jp.wasabeef.transformers:picasso-gpu:0.0.2"
    const val coil = "jp.wasabeef.transformers:coil:0.0.2"
    const val coilGpu = "jp.wasabeef.transformers:coil-gpu:0.0.2"
  }
}
