package com.pratham.headytest.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String,
    val server_id: Int
)