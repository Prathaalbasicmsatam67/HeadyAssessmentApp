package com.pratham.headytest.db.dao

import androidx.room.*
import com.pratham.headytest.db.tables.SubCategoryTable

@Dao
interface SubCategoryDao {
    @Query("SELECT * FROM sub_category")
    suspend fun allSubCategories(): List<SubCategoryTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(subCategoryTable: SubCategoryTable): Long

    @Delete
    suspend fun remove(subCategoryTable: SubCategoryTable)

    @Query("SELECT id from sub_category")
    suspend fun getSubCategoryIds(): List<Long>

    @Query("DELETE FROM sub_category")
    suspend fun clear()
}