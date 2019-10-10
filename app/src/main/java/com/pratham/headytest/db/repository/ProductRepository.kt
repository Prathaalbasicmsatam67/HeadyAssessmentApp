package com.pratham.headytest.db.repository

import com.pratham.headytest.db.dao.ProductDao
import com.pratham.headytest.db.model.JoinProductVariantTable
import com.pratham.headytest.db.tables.ProductTable
import io.reactivex.Flowable
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao: ProductDao) {

    fun getAllProductList(): Flowable<List<JoinProductVariantTable>> {
        return productDao.getAllProductWithVariants()
    }


    fun getAllProductListWithCategory(categoryId: Long?): Flowable<List<JoinProductVariantTable>> {
        return productDao.getAllProductWithVariantsForCategory(categoryId)
    }

    fun getAllProductListWithRanking(rankingId: Int?): Flowable<List<JoinProductVariantTable>> {
        return productDao.getAllProductWithVariantsForRanking(rankingId)
    }

    fun getAllProduct(): Flowable<List<ProductTable>> {
        return productDao.allProducts()
    }
}