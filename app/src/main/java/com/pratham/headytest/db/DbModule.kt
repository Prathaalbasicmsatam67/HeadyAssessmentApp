package com.pratham.headytest.db

import android.content.Context
import androidx.room.Room
import com.pratham.headytest.db.dao.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DbModule {
    @JvmStatic
    @Singleton
    @Provides
    fun provideDatabase(context: Context): HeadyDatabase {
        return Room.databaseBuilder(
            context,
            HeadyDatabase::class.java, HeadyDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideProductDao(database: HeadyDatabase): ProductDao {
        return database.productDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCategoryDao(database: HeadyDatabase): CategoryDao {
        return database.categoryDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideProductVariantDao(database: HeadyDatabase): ProductVariantDao {
        return database.productVariantDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRankingDao(database: HeadyDatabase): RankingDao {
        return database.rankingDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideProductRankingDao(database: HeadyDatabase): ProductRankingDao {
        return database.productRankingDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideSubCategoryDao(database: HeadyDatabase): SubCategoryDao {
        return database.subcategoryDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideTaxDao(database: HeadyDatabase): TaxDao {
        return database.taxDao()
    }
}