package com.coooldoggy.imagesearch.ui.adapter.paging

import androidx.recyclerview.widget.DiffUtil
import com.coooldoggy.imagesearch.framework.model.Documents

object DocumentComparator: DiffUtil.ItemCallback<Documents>() {
    override fun areItemsTheSame(oldItem: Documents, newItem: Documents): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: Documents, newItem: Documents): Boolean {
        return oldItem == newItem
    }
}