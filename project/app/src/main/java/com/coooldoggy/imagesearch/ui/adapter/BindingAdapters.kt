package com.coooldoggy.imagesearch.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @androidx.databinding.BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?) {
        if (url.isNullOrEmpty()) return
        Glide.with(view.context).load(url)
            .centerCrop()
            .into(view)
    }

    @BindingAdapter("android:visibleIf")
    @JvmStatic
    fun View.setVisibleIf(value: Boolean) {
        isVisible = value
    }
}