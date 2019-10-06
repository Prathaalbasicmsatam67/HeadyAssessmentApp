package com.pratham.headytest.db.repository

import com.pratham.headytest.db.dao.*
import com.pratham.headytest.db.tables.CategoryTable
import com.pratham.headytest.services.ApiService
import com.pratham.headytest.ui.splash.model.HeadyDataApiResponse
import com.pratham.headytest.ui.splash.model.HeadyDataUiModel
import io.reactivex.Flowable
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeadyDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao,
    private val subCategoryDao: SubCategoryDao,
    private val productVariantDao: ProductVariantDao,
    private val rankingDao: RankingDao

) {

    fun getAllApiData(): Flowable<HeadyDataApiResponse> {
        return apiService.getHeadyData();
    }

    fun storeHeadyData(data: HeadyDataUiModel): Observable<Boolean> {
        data.categories.listIterator().forEach { category ->
            {
                val categoryData = CategoryTable(
                    id = null,
                    name = category.name,
                    server_id = category.id
                )
                CoroutineScope(Dispatchers.IO).launch { categoryDao.insert(categoryData) }


            }
        }

        return Observable.just(true);
    }


}