package com.pratham.headytest.ui.home

import androidx.lifecycle.MutableLiveData
import com.pratham.headytest.app.BaseViewModelImpl
import com.pratham.headytest.ui.categoryfilter.model.CategoryUiModel
import com.pratham.headytest.ui.home.model.RankingUiModel
import com.pratham.headytest.ui.model.ProductUiModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getAllRankingUseCase: GetAllRankingUseCase,
    private val getAllProductUseCase: GetAllProductsUseCase,
    private val getAllProductsForCategory: GetAllProductsForCategory,
    private val getAllProductsForRankingUseCase: GetAllProductsForRankingUseCase,
    compositeDisposable: CompositeDisposable
) : BaseViewModelImpl(compositeDisposable) {


    val getAllRanking = MutableLiveData<List<RankingUiModel>>()

    val getAllProducts = MutableLiveData<List<ProductUiModel?>>()

    val getAllProductsForCategoryLiveData = MutableLiveData<List<ProductUiModel?>>()

    val getAllProductsForRankingLiveData = MutableLiveData<List<ProductUiModel?>>()

    fun getAllRanking() {
        manageActionDisposables(
            getAllRankingUseCase.execute("")
                .subscribe(
                    {
                        val list: MutableList<RankingUiModel> = it.toMutableList()
                        val obj = RankingUiModel("Select Rank", 0)
                        list.add(0, obj)

                        getAllRanking.value = list
                    },
                    { t: Throwable? ->
                        t?.printStackTrace()
                    }
                )
        )

    }

    fun getAllProducts() {
        manageActionDisposables(
            getAllProductUseCase.execute("")
                .subscribe({

                    val listOfProductUiModel = mutableListOf<ProductUiModel?>()
                    it.values.toList().forEach { data ->
                        listOfProductUiModel.add(data)
                    }

                    getAllProducts.value = listOfProductUiModel
                },
                    { t: Throwable? ->
                        t?.printStackTrace()
                    })
        )
    }

    fun getAllProductForCategory(categoryUiModel: CategoryUiModel) {
        manageActionDisposables(
            getAllProductsForCategory.execute(categoryUiModel.catLocalId)
                .subscribe({

                    val listOfProductUiModel = mutableListOf<ProductUiModel?>()
                    it.values.toList().forEach { data ->
                        listOfProductUiModel.add(data)
                    }

                    getAllProductsForCategoryLiveData.value = listOfProductUiModel
                },
                    { t: Throwable? ->
                        t?.printStackTrace()
                    })
        )
    }


    fun getAllProductForRanking(rankingId: Int?) {
        manageActionDisposables(
            getAllProductsForRankingUseCase.execute(rankingId)
                .subscribe({

                    val listOfProductUiModel = mutableListOf<ProductUiModel?>()
                    it.values.toList().forEach { data ->
                        listOfProductUiModel.add(data)
                    }

                    getAllProductsForRankingLiveData.value = listOfProductUiModel
                },
                    { t: Throwable? ->
                        t?.printStackTrace()
                    })
        )
    }


}