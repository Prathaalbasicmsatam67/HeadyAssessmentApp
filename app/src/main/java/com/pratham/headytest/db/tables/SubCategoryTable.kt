package com.pratham.headytest.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sub_category",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = CategoryTable::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class SubCategoryTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val value: String,
    @ColumnInfo(index = true)
    val categoryId: Long
)