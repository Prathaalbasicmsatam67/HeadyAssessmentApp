package com.pratham.headytest.ui.home

import com.pratham.headytest.app.UseCase
import com.pratham.headytest.db.model.JoinProductVariantTable
import com.pratham.headytest.db.repository.ProductRepository
import com.pratham.headytest.rx.IoThreadScheduler
import com.pratham.headytest.rx.MainThreadScheduler
import com.pratham.headytest.rx.SchedulerProvider
import com.pratham.headytest.ui.model.ProductUiModel
import com.pratham.headytest.ui.model.Variants
import io.reactivex.Flowable
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductRepository,
    @IoThreadScheduler subscribeOnScheduler: SchedulerProvider,
    @MainThreadScheduler observeOnScheduler: SchedulerProvider
) : UseCase<String, List<ProductUiModel?>>(subscribeOnScheduler, observeOnScheduler) {


    override fun createObservable(request: String): Flowable<List<ProductUiModel?>> {
        val hashmap = HashMap<Long, ProductUiModel?>()

        repository.getAllProductList()
            .map { t ->
                t.map { response: JoinProductVariantTable ->

                    if (hashmap.get(response?.productId) == null) {
                        val variantList = mutableListOf<Variants>()
                        val variant = Variants(
                            response.variantId,
                            response.variantColor,
                            response.variantSize,
                            response.variantPrice
                        )
                        variantList.add(variant)

                        val productUiModel = ProductUiModel(
                            response.productId,
                            response.productName,
                            response.productServerId,
                            variantList
                        )
                        val uiModel = hashmap.put(response.productId, productUiModel)

                    } else {
                        val productUiModel = hashmap.get(response.productId)

                        val variant = Variants(
                            response.variantId,
                            response.variantColor,
                            response.variantSize,
                            response.variantPrice
                        )
                        productUiModel?.variants?.toMutableList()?.add(variant)

                        val uiModel = hashmap.put(response.productId, productUiModel)
                    }

                }

            }

        val listOfProductUiModel = mutableListOf<ProductUiModel?>()
        hashmap.toList().forEach { data ->
            listOfProductUiModel.add(data.second)

        }

        return Flowable.just(listOfProductUiModel.toList());
    } //close method

}