/**
 * Copyright (C) 2020 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.wasabeef.composable.coil

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.graphics.drawscope.drawCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.ContextAmbient
import androidx.core.graphics.drawable.toBitmap
import coil.Coil
import coil.request.ImageRequest
import coil.size.OriginalSize
import coil.size.PixelSize
import coil.size.Scale
import coil.target.Target

@Composable
fun CoilImage(
  model: Any,
  modifier: Modifier = Modifier,
  customize: ImageRequest.Builder.() -> Unit = {}
) {
  WithConstraints(modifier) {
    val width =
      if (constraints.maxWidth > 0 && constraints.maxWidth < Int.MAX_VALUE) constraints.maxWidth
      else 0
    val height =
      if (constraints.maxHeight > 0 && constraints.maxHeight < Int.MAX_VALUE) constraints.maxHeight
      else 0

    val size = if (width == 0 || height == 0) OriginalSize else PixelSize(width, height)

    val image = remember { mutableStateOf<ImageAsset?>(null) }
    val drawable = remember { mutableStateOf<Drawable?>(null) }
    val context = ContextAmbient.current
    onCommit(model) {
      val target = object : Target {
        override fun onStart(placeholder: Drawable?) {
          drawable.value = placeholder
        }

        override fun onError(error: Drawable?) {
          drawable.value = error
        }

        override fun onSuccess(result: Drawable) {
          image.value = result.toBitmap().asImageAsset()
        }
      }

      val request = ImageRequest.Builder(context)
        .data(model)
        .size(size)
        .scale(Scale.FILL)
        .apply { customize(this) }
        .target(target)

      val requestDisposable = Coil.imageLoader(context).enqueue(request.build())

      onDispose {
        image.value = null
        drawable.value = null
        requestDisposable.dispose()
      }
    }

    if (image.value != null) {
      Image(asset = image.value!!, modifier = modifier)
    } else if (drawable.value != null) {
      Canvas(modifier = modifier) {
        drawCanvas { canvas, _ -> drawable.value!!.draw(canvas.nativeCanvas) }
      }
    }
  }
}