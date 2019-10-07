package com.pratham.headytest.db.repository

import android.util.Log
import com.pratham.headytest.db.dao.*
import com.pratham.headytest.db.tables.*
import com.pratham.headytest.services.ApiService
import com.pratham.headytest.ui.model.HeadyDataApiResponse
import com.pratham.headytest.ui.model.HeadyDataUiModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class HeadyDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao,
    private val subCategoryDao: SubCategoryDao,
    private val productVariantDao: ProductVariantDao,
    private val taxDao: TaxDao,
    private val rankingDao: RankingDao,
    private val productRankingDao: ProductRankingDao
) {

    fun getAllApiData(): Flowable<HeadyDataApiResponse> {
        return apiService.getHeadyData();
    }

    fun storeHeadyData(data: HeadyDataUiModel): Flowable<Boolean> {

        // iterate categories data
        data.categories.listIterator().forEach { category ->

            val categoryData = CategoryTable(
                id = null,
                name = category.name,
                server_id = category.id
            )

            val insertedId = categoryDao.insert(categoryData)

            Log.d(
                "HeadyDataRepository",
                "Inserted Cat Id : " + insertedId
            )

            /*
            If Subcategories present in the list then we are inserting it
             */
            if (!category.child_categories.isEmpty()) {
                // insert sub category with category id
                category.child_categories.forEach { subCatData ->

                    val subCatTableRow = SubCategoryTable(
                        id = null,
                        value = subCatData,
                        categoryId = insertedId
                    )

                    subCategoryDao.insert(subCatTableRow)

                }
            }

            // Insert products if list is not empty
            if (!category.products.isEmpty()) {
                category.products.forEach { productData ->
                    // product contains tax, variants
                    // so we will map product inserted id with variant and tax

                    val productRow = ProductTable(
                        id = null,
                        name = productData.name,
                        server_id = productData.id
                    )

                    val insertedProductId = productDao.insert(productRow)
                    Log.d(
                        "HeadyDataRepository",
                        "Inserted Product Id : " + insertedProductId
                    )

                    // add tax details of product
                    if (productData.tax?.name != null) {
                        val taxRow = TaxTable(
                            id = null,
                            name = productData.tax.name,
                            taxPercent = productData.tax.value,
                            productId = insertedProductId
                        )

                        val insertedTaxId = taxDao.insert(taxRow)
                        Log.d(
                            "HeadyDataRepository",
                            "Inserted Tax Id : " + insertedTaxId
                        )
                    }


                    // add variants of product
                    if (!productData.variants.isEmpty()) {
                        productData.variants.forEach { variantData ->
                            val variantRow = ProductVariantTable(
                                id = null,
                                color = variantData.color,
                                size = variantData.size,
                                price = variantData.price,
                                productId = insertedProductId

                            )

                            val productVarId = productVariantDao.insert(variantRow)
                            Log.d(
                                "HeadyDataRepository",
                                "Inserted ProductVariant Id : " + productVarId
                            )

                        }
                    }
                } // product insertion for loop
            }


        }

        // iterate ranking data
        data.rankings.forEach { ranking ->

            // insert ranking details
            val rankingData = RankingTable(
                id = null,
                name = ranking.ranking
            )

            // insert ranking
            val insertedId = rankingDao.insert(rankingData)

            // product and ranking conf
            ranking.products.forEach { product ->

                val rankingProductRow = ProductRankingTable(
                    id = null,
                    productServerId = product.id,
                    ranking_id = insertedId
                )
                productRankingDao.insert(rankingProductRow)

            }


        }

        return Observable.just(true).toFlowable(BackpressureStrategy.LATEST);
    }


}