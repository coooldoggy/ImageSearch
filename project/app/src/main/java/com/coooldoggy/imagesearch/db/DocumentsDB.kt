package com.coooldoggy.imagesearch.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coooldoggy.imagesearch.framework.model.Documents


@Database(
    entities = [Documents::class],
    version = 1,
    exportSchema = false
)
abstract class DocumentsDB : RoomDatabase() {
    companion object {
        fun create(context: Context, useInMemory: Boolean): DocumentsDB {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, DocumentsDB::class.java)
            } else {
                Room.databaseBuilder(context, DocumentsDB::class.java, "documents_table.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun posts(): DocumentsDao
}