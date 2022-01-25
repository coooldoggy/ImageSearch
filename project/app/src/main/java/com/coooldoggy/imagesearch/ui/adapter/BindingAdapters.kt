package com.coooldoggy.imagesearch.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide

object BindingAdapters {
    @androidx.databinding.BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context).load(url)
            .into(view)
    }
}