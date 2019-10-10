package com.pratham.headytest.db.tables

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product",

    indices = arrayOf(
        Index(
            value = arrayOf("id", "server_id"),
            unique = true
        )
    )
)
data class ProductTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String,
    val server_id: Int,
    val categoryId: Long
)