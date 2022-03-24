package com.joblogic.data.product.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joblogic.data.product.entity.ProductEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveItem(item: ProductEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveItem(item: MutableList<ProductEntity>): Single<MutableList<Long>>

    @Query("SELECT * FROM ItemToSell ORDER BY id")
    fun getSellList(): Single<MutableList<ProductEntity>>
}