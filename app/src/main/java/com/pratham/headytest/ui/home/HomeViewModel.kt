package com.pratham.headytest.ui.home

import androidx.lifecycle.MutableLiveData
import com.pratham.headytest.app.BaseViewModelImpl
import com.pratham.headytest.ui.home.model.RankingUiModel
import com.pratham.headytest.ui.model.ProductUiModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getAllRankingUseCase: GetAllRankingUseCase,
    private val getAllProductUseCase: GetAllProductsUseCase,
    compositeDisposable: CompositeDisposable
) : BaseViewModelImpl(compositeDisposable) {


    val getAllRanking = MutableLiveData<List<RankingUiModel>>()

    val getAllProducts = MutableLiveData<List<ProductUiModel?>>()

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


}