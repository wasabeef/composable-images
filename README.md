<p align="center">

  <a href="https://developer.android.com/jetpack/compose">
    <img src="https://github.com/wasabeef/composable-images/raw/main/art/jetpack-compose.svg" width="480px"/>
  </a>
</p>
<p align="center">
  <a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img src="https://img.shields.io/badge/license-Apache%202-blue.svg" />
  </a>
  <a href="https://bintray.com/wasabeef/maven/composable-images/_latestVersion">
    <img src="https://api.bintray.com/packages/wasabeef/maven/composable-images/images/download.svg" />
  </a>
  <a href="https://github.com/wasabeef/composable-images/actions">
    <img src="https://github.com/wasabeef/composable-images/workflows/Android%20CI/badge.svg" />
  </a>
</p>

## What is Composable Images?

A simple library for loading into Jetpack Compose Image using for [Coil], [Glide], and [Picasso].

<a href="https://github.com/coil-kt/coil">
  <img src="https://github.com/wasabeef/composable-images/raw/main/art/coil.png" width="58px"/>
</a>
<a href="https://github.com/bumptech/glide">
  <img src="https://github.com/wasabeef/composable-images/raw/main/art/glide.png" width="64px"/>
</a>
<a href="https://github.com/square/picasso">
  <img src="https://github.com/wasabeef/composable-images/raw/main/art/picasso.jpg" width="64px"/>
</a>
<a href="https://developer.android.com/jetpack/compose">
  <img src="https://github.com/wasabeef/composable-images/raw/main/art/jetpack-compose.svg" width="100px"/>
</a>


## Installation

### Requirements
- Android 5.0+ Lollipop (API level 21)

### Repository
```gradle
repositories {
  jcenter()
}
```

#### For [Coil] <a href="https://github.com/bumptech/glide"><img src="https://github.com/wasabeef/composable-images/raw/main/art/coil.png" width="12px"/></a>
```gradle
dependencies {
  implementation 'jp.wasabeef.composable:coil:1.x.x'
}
```
```kotlin
CoilImage(
  model = "https://images.unsplash.com/photo-1588952159215-a4b39193464e",
  modifier = Modifier.preferredWidth(240.dp)
) {
  transformations(
    BlurTransformation(context, 25f, 2f)
  )
}
```

#### For [Glide] <a href="https://github.com/coil-kt/coil"><img src="https://github.com/wasabeef/composable-images/raw/main/art/glide.png" width="12px"/></a>
```gradle
dependencies {
  implementation 'jp.wasabeef.composable:glide:1.x.x'
}
```
```kotlin
GlideImage(
  model = "https://images.unsplash.com/photo-1588952159215-a4b39193464e",
  modifier = Modifier.preferredWidth(120.dp),
  options = RequestOptions().centerCrop())
```

#### For [Picasso] <a href="https://github.com/square/picasso"><img src="https://github.com/wasabeef/composable-images/raw/main/art/picasso.jpg" width="12px"/></a>
```gradle
dependencies {
  implementation 'jp.wasabeef.composable:picasso:1.x.x'
}
```
```kotlin
PicassoImage(
  model = "https://images.unsplash.com/photo-1588952159215-a4b39193464e",
  modifier = Modifier.preferredWidth(120.dp),
) {
  centerInside()
  rotate(90f)
}
```

## Development

### Setup 

Things you will need

- Linux, Mac OS X, or Windows.
- [Android Studio (Preview)](https://developer.android.com/studio/preview)
- npm

```
$ npm install
```

### Build

```
$ ./gradlew assemble
```

### Formatting

```
$ ./gradlew ktlint
```

### Publishing to [Bintray](https://bintray.com/bintray/jcenter)

```
$ ./gradlew clean install build
$ ./gradlew bintrayUpload -PbintrayUser=******** -PbintrayKey=***************
```



[Coil]: https://github.com/coil-kt/coil
[Glide]: https://github.com/bumptech/glide
[Picasso]: https://github.com/square/picasso
[Fresco]: https://github.com/facebook/fresco
