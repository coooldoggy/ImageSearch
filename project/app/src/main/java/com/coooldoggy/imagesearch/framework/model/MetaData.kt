package com.coooldoggy.imagesearch.framework.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MetaData(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("pageable_count")
    val pageableCount: Int,

    @SerializedName("is_end")
    val isEnd: Boolean
): Serializable