package com.pratham.headytest.db.dao

import androidx.room.*
import com.pratham.headytest.db.tables.ProductTable
import io.reactivex.Flowable

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun allProducts(): Flowable<List<ProductTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductTable)

    @Delete
    suspend fun remove(product: ProductTable)

    @Query("SELECT id from product")
    suspend fun getProductIds(): List<Long>

    @Query("DELETE FROM product")
    suspend fun clear()
}