package com.pratham.headytest.ui.categoryfilter

import androidx.lifecycle.MutableLiveData
import com.pratham.headytest.app.BaseViewModelImpl
import com.pratham.headytest.ui.categoryfilter.model.CategoryUiModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class CategoryViewModel @Inject constructor(
    private val getAllCatUseCase: GetAllCategoryUseCase,
    compositeDisposable: CompositeDisposable
) : BaseViewModelImpl(compositeDisposable) {

    val getAllCatLiveData = MutableLiveData<List<CategoryUiModel>>()

    fun getAllDataList() {
        manageActionDisposables(
            getAllCatUseCase.execute("")
                .subscribe(
                    {
                        getAllCatLiveData.value = it
                    },
                    { t: Throwable? ->
                        t?.printStackTrace()
                    }
                )
        )

    }

}