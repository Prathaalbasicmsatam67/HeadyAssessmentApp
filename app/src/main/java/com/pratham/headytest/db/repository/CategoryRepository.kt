package com.pratham.headytest.db.repository

import com.pratham.headytest.db.dao.CategoryDao
import com.pratham.headytest.db.dao.SubCategoryDao
import com.pratham.headytest.db.tables.CategoryTable
import io.reactivex.Flowable
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao,
    private val subCategoryDao: SubCategoryDao
) {


    fun getAllCategories(): Flowable<List<CategoryTable>> {
        return Flowable.just(categoryDao.allCategory());
    }

}