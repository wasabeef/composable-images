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

package jp.wasabeef.composable.picasso

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import com.squareup.picasso.Target

@Composable
fun PicassoImage(
  model: String,
  modifier: Modifier = Modifier.fillMaxWidth(),
  request: RequestCreator.() -> RequestCreator = { this },
) {
  WithConstraints(modifier) {
    val width =
      if (constraints.maxWidth > 0 && constraints.maxWidth < Int.MAX_VALUE) constraints.maxWidth
      else 0
    val height =
      if (constraints.maxHeight > 0 && constraints.maxHeight < Int.MAX_VALUE) constraints.maxHeight
      else 0

    val image = remember { mutableStateOf<ImageAsset?>(null) }
    val drawable = remember { mutableStateOf<Drawable?>(null) }

    onCommit(model) {
      val target = object : Target {
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
          drawable.value = placeHolderDrawable
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
          drawable.value = errorDrawable
        }

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
          image.value = bitmap?.asImageAsset()
        }
      }

      Picasso.get()
        .load(model)
        .resize(width, height)
        .let(request)
        .into(target)

      onDispose {
        image.value = null
        drawable.value = null
        Picasso.get().cancelRequest(target)
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