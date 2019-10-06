package com.pratham.headytest.ui.home

import androidx.lifecycle.MutableLiveData
import com.pratham.headytest.app.BaseViewModelImpl
import com.pratham.headytest.ui.home.model.HeadyDataUiModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getAllDataListUseCase: GetAllDataListUseCase,
    compositeDisposable: CompositeDisposable
) : BaseViewModelImpl(compositeDisposable) {

    val getAllDataLiveData = MutableLiveData<HeadyDataUiModel>()

    fun getAllDataList() {
        manageActionDisposables(
            getAllDataListUseCase.execute("")
                .subscribe(
                    {
                        getAllDataLiveData.value = it
                    },
                    { t: Throwable? ->
                        t?.printStackTrace()
                    }
                )
        )

    }

}