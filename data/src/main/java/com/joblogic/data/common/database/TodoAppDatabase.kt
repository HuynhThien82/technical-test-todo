package com.joblogic.data.common.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joblogic.data.product.datasource.local.dao.ProductDao
import com.joblogic.data.product.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class TodoAppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object{
        private const val DATABASE_NAME = "todo_app_db.db"
        @Volatile // Make sure instance visible correctly between different thread
        private var instance: TodoAppDatabase? = null
        private val LOCK = Any()

        // make sure only one thread can create database at the same time
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                TodoAppDatabase::class.java,
                DATABASE_NAME
            ).build()
            return instance!!
        }
    }
}