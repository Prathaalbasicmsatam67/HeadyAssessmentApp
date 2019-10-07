package com.pratham.headytest.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tax_details",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ProductTable::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("productId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class TaxTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String,
    val taxPercent: Double,
    @ColumnInfo(index = true)
    val productId: Long
)