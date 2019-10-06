package com.pratham.headytest.db.dao

import androidx.room.*
import com.pratham.headytest.db.tables.ProductVariantTable

@Dao
interface ProductVariantDao {
    @Query("SELECT * FROM product_variant_table")
    suspend fun allProductVariants(): List<ProductVariantTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productVariant: ProductVariantTable)

    @Delete
    suspend fun remove(productVariant: ProductVariantTable)

    @Query("SELECT id from product_variant_table")
    suspend fun getProductVariantIds(): List<Long>

    @Query("DELETE FROM product_variant_table")
    suspend fun clear()
}