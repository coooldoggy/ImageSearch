package com.coooldoggy.imagesearch.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.coooldoggy.imagesearch.databinding.ItemImageBinding
import com.coooldoggy.imagesearch.framework.model.Documents

class ImageViewAdapter(differCallback: DiffUtil.ItemCallback<Documents>): PagingDataAdapter<Documents, ImageViewAdapter.ImageViewHolder>(differCallback) {

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(parent)
    }


    inner class ImageViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent) {
        private val binding = ItemImageBinding.bind(parent)
        fun bind(item: Documents?) {
            binding.apply {
                //TODO
            }
        }
    }
}
