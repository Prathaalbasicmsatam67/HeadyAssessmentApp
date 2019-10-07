package com.pratham.headytest.db.dao

import androidx.room.*
import com.pratham.headytest.db.tables.TaxTable

@Dao
interface TaxDao {
    @Query("SELECT * FROM tax_details")
    suspend fun allTaxDetails(): List<TaxTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tax: TaxTable): Long

    @Delete
    suspend fun remove(tax: TaxTable)

    @Query("SELECT id from tax_details")
    suspend fun getAllTaxIds(): List<Long>

    @Query("DELETE FROM tax_details")
    suspend fun clear()
}