package com.coooldoggy.imagesearch.framework.api

import com.coooldoggy.imagesearch.ImageSearchApplication
import com.coooldoggy.imagesearch.R
import com.coooldoggy.imagesearch.framework.model.ImageResponse
import com.coooldoggy.imagesearch.framework.service.ImageSearchService
import retrofit2.Response

object ApiManager {
    private val TAG = ApiManager::class.java.simpleName
    private val imageSearchService by lazy { ImageSearchService.createService(ImageSearchService::class.java) }

    suspend fun queryImage(query: String): Response<ImageResponse>{
        return imageSearchService.queryImage(
            key = ImageSearchApplication.getContext()?.getString(R.string.kakao_app_key) ?: "",
            query = query)
    }
}