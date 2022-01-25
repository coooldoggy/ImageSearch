package com.coooldoggy.imagesearch.framework.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Documents(
    @SerializedName("collection")
    val collection: String,

    @SerializedName("thumbnail_url")
    val thumbNailUrl: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int,

    @SerializedName("display_sitename")
    val displaySiteName: String,

    @SerializedName("doc_url")
    val docUrl: String,

    @SerializedName("datetime")
    val dateTime: String
): Serializable
