package com.pratham.headytest.ui.splash

import androidx.lifecycle.MutableLiveData
import com.pratham.headytest.app.BaseViewModelImpl
import com.pratham.headytest.ui.splash.model.HeadyDataUiModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val getAllDataListUseCase: GetAllDataListUseCase,
    private val validateIsDbDataUseCase: ValidateIsDbDataUseCase,
    compositeDisposable: CompositeDisposable
) : BaseViewModelImpl(compositeDisposable) {

    val getAllDataLiveData = MutableLiveData<HeadyDataUiModel>()

    val validateDataLiveData = MutableLiveData<Boolean>()

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

    fun validateIsDbEmpty() {
        manageActionDisposables(
            validateIsDbDataUseCase.execute("")
                .subscribe(
                    {
                        validateDataLiveData.value = it.isEmpty()
                    },
                    { t: Throwable? ->
                        t?.printStackTrace()
                    })
        )
    }

}