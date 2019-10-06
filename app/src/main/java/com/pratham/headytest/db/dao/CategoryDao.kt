package com.pratham.headytest.db.dao

import androidx.room.*
import com.pratham.headytest.db.tables.CategoryTable

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    suspend fun allCategory(): List<CategoryTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: CategoryTable)

    @Delete
    suspend fun remove(category: CategoryTable)

    @Query("SELECT id from category")
    suspend fun getCategoryIds(): List<Long>

    @Query("DELETE FROM category")
    suspend fun clear()
}