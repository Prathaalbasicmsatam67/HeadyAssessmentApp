package com.pratham.headytest.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_ranking_table",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = RankingTable::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("ranking_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class ProductRankingTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val ranking_id: Long,
    @ColumnInfo(index = true)
    val productId: Long
)