package com.coooldoggy.imagesearch.db

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bumptech.glide.load.HttpException
import com.coooldoggy.imagesearch.ImageSearchApplication
import com.coooldoggy.imagesearch.R
import com.coooldoggy.imagesearch.framework.model.Documents
import com.coooldoggy.imagesearch.framework.service.ImageSearchService
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class DocumentsRemoteMediator(
    private val database: DocumentsDB,
    private val networkService: ImageSearchService,
    private val query: String
) : RemoteMediator<Int, Documents>() {
    private val documentsDao = database.posts()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Documents>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    lastItem.docUrl
                }
            }

            val response = networkService.queryImage(
                key = ImageSearchApplication.getContext()?.getString(R.string.kakao_app_key) ?: "",query = query
            )

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    documentsDao.clearAll()
                }

                response.body()?.documents?.let { documentsDao.insertAll(it.toList()) }
            }

            MediatorResult.Success(
                endOfPaginationReached = response.body()?.meta?.isEnd == true
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}