package com.coooldoggy.imagesearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.coooldoggy.imagesearch.R
import com.coooldoggy.imagesearch.databinding.ItemImageBinding
import com.coooldoggy.imagesearch.framework.model.Documents

class ImageViewAdapter(differCallback: DiffUtil.ItemCallback<Documents>): PagingDataAdapter<Documents, ImageViewAdapter.ImageViewHolder>(differCallback) {

    interface OnClickImage{
        fun onClickImageMoveToDetail(data: Documents?)
    }
    var onClickImage: OnClickImage? = null

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = try {
            LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        }catch (e: Throwable){
            View(parent.context)
        }
        return ImageViewHolder(view)
    }


    inner class ImageViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        var binding: ItemImageBinding = ItemImageBinding.bind(parent)
        fun bind(item: Documents?) {
            binding.apply {
                model = item
                ivImage.setOnClickListener {
                    onClickImage?.onClickImageMoveToDetail(item)
                }
            }
        }
    }
}
