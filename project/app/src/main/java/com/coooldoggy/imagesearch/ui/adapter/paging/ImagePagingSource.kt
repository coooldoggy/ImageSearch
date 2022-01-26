package com.coooldoggy.imagesearch.ui.adapter.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.coooldoggy.imagesearch.ImageSearchApplication
import com.coooldoggy.imagesearch.R
import com.coooldoggy.imagesearch.framework.model.Documents
import com.coooldoggy.imagesearch.framework.service.ImageSearchService

class ImagePagingSource(val imageSearchService: ImageSearchService, val query: String) : PagingSource<Int, Documents>(){
    companion object{
        private val TAG = ImagePagingSource::class.java.simpleName
    }

    override fun getRefreshKey(state: PagingState<Int, Documents>): Int? {
        return state.anchorPosition?.let {  anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Documents> {
        try {
            val nextPageNumber = params.key ?: 1
            Log.d(TAG, "nextPageNumber $nextPageNumber")
            val response = imageSearchService.queryImage(key = ImageSearchApplication.getContext()?.getString(R.string.kakao_app_key) ?: "",
                query= query, page = nextPageNumber)
            return LoadResult.Page(
                data = response.body()?.documents ?: emptyList(),
                prevKey = null,
                nextKey = if (response.body()?.meta?.isEnd == true) null else nextPageNumber.plus(1)
            )
        }catch (e: Exception){
           e.printStackTrace()
        }
        return LoadResult.Invalid()
    }

}