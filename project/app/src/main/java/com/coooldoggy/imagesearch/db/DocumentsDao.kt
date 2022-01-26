package com.coooldoggy.imagesearch.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.coooldoggy.imagesearch.framework.model.Documents

@Dao
interface DocumentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(documents: List<Documents>)

    @Query("SELECT * FROM documents_table WHERE label LIKE :query")
    fun pagingSource(query: String): PagingSource<Int, Documents>

    @Query("DELETE FROM documents_table")
    suspend fun clearAll()

}