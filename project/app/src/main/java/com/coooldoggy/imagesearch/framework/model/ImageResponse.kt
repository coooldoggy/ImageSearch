package com.coooldoggy.imagesearch.framework.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageResponse(
    @SerializedName("meta")
    val meta: MetaData,

    @SerializedName("documents")
    val documents: ArrayList<Documents>

) : Serializable
