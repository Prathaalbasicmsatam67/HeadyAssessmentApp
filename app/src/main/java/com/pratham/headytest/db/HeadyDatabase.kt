package com.pratham.headytest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pratham.headytest.BuildConfig
import com.pratham.headytest.db.dao.*
import com.pratham.headytest.db.tables.*

@Database(
    entities = [ProductTable::class,
        CategoryTable::class,
        ProductVariantTable::class,
        RankingTable::class,
        SubCategoryTable::class,
        TaxTable::class
    ],
    version = BuildConfig.DatabaseVersion,
    exportSchema = false
)
abstract class HeadyDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun categoryDao(): CategoryDao

    abstract fun productVariantDao(): ProductVariantDao

    abstract fun rankingDao(): RankingDao

    abstract fun productRankingDao(): ProductRankingDao

    abstract fun subcategoryDao(): SubCategoryDao

    abstract fun taxDao(): TaxDao

    companion object {
        const val DATABASE_NAME = "headyAssessment.db"
    }
}