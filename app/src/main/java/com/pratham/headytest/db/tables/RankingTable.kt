package com.pratham.headytest.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ranking")
data class RankingTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
)