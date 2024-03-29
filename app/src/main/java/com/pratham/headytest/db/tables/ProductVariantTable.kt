package com.pratham.headytest.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_variant_table",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ProductTable::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("productId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class ProductVariantTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val color: String,
    val size: Int,
    val price: Int,
    @ColumnInfo(index = true)
    val productId: Long
)