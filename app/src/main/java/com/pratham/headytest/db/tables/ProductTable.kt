package com.pratham.headytest.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val server_id: Long
)