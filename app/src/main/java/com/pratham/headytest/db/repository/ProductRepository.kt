package com.pratham.headytest.db.repository

import com.pratham.headytest.db.dao.ProductDao
import com.pratham.headytest.db.tables.ProductTable
import io.reactivex.Flowable
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao: ProductDao) {

    fun productList(): Flowable<List<ProductTable>> {
        return productDao.allProducts()
    }
}