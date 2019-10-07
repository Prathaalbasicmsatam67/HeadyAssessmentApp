package com.pratham.headytest.db.dao

import androidx.room.*
import com.pratham.headytest.db.tables.ProductRankingTable


@Dao
interface ProductRankingDao {
    @Query("SELECT * FROM product_ranking_table")
    suspend fun allProductRankings(): List<ProductRankingTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rankingTable: ProductRankingTable)

    @Delete
    suspend fun remove(rankingTable: ProductRankingTable)

    @Query("SELECT id from product_ranking_table")
    suspend fun getProductRankingIds(): List<Long>

    @Query("DELETE FROM product_ranking_table")
    suspend fun clear()
}