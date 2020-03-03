package com.wolfspider.zeus.sdk.media

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ImageUtils {

    fun loadImage(
        targetView: ImageView,
        filePath: String
    ) {
        Glide
            .with(targetView.context)
            .load(filePath)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(targetView)
    }

}