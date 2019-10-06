package com.pratham.headytest.db.dao

import androidx.room.*
import com.pratham.headytest.db.tables.RankingTable

@Dao
interface RankingDao {
    @Query("SELECT * FROM ranking")
    suspend fun allRankings(): List<RankingTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rankingTable: RankingTable)

    @Delete
    suspend fun remove(rankingTable: RankingTable)

    @Query("SELECT id from ranking")
    suspend fun getRankingIds(): List<Long>

    @Query("DELETE FROM ranking")
    suspend fun clear()
}