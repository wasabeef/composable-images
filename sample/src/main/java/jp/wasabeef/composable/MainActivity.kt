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

package jp.wasabeef.composable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.composable.coil.CoilImage
import jp.wasabeef.composable.glide.GlideImage
import jp.wasabeef.composable.picasso.PicassoImage
import jp.wasabeef.transformers.coil.CropCenterTransformation
import jp.wasabeef.transformers.coil.MaskTransformation
import jp.wasabeef.transformers.glide.BlurTransformation
import jp.wasabeef.transformers.glide.gpu.PixelationFilterTransformation

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      body()
    }
  }
}

@Composable
fun body() {
  val context = ContextAmbient.current
  MaterialTheme {
    ScrollableColumn(modifier = Modifier.padding(12.dp)) {
      ScrollableRow {
        PicassoImage(
          model = "https://images.unsplash.com/photo-1588952159215-a4b39193464e",
          modifier = Modifier.preferredWidth(120.dp),
        ) {
          centerInside()
          rotate(90f)
        }
        PicassoImage(
          model = "https://images.unsplash.com/photo-1588952159215-a4b39193464e",
          modifier = Modifier.preferredWidth(240.dp))
      }

      ScrollableRow {
        GlideImage(
          model = "https://images.unsplash.com/photo-1588952159215-a4b39193464e",
          modifier = Modifier.preferredWidth(120.dp),
          options = RequestOptions().transform(
            BlurTransformation(
              context = context,
              25,
              4
            )
          )
        )
        GlideImage(
          model = "https://images.unsplash.com/photo-1588952159215-a4b39193464e",
          modifier = Modifier.preferredWidth(240.dp))
      }

      ScrollableRow {
        CoilImage(
          model = "https://images.unsplash.com/photo-1588952159215-a4b39193464e",
          modifier = Modifier.preferredWidth(240.dp),
        ) {
          transformations(
            CropCenterTransformation(),
            MaskTransformation(context, R.drawable.mask_starfish)
          )
        }
        CoilImage(
          model = "https://images.unsplash.com/photo-1588952159215-a4b39193464e",
          modifier = Modifier.preferredWidth(240.dp)
        )
      }
    }
  }
}

@Preview
@Composable
fun preview() {
  body()
}