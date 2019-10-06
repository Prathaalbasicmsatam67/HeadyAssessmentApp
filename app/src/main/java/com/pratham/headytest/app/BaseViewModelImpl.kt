package com.pratham.headytest.app

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModelImpl(
    private val compositeDisposable: CompositeDisposable
) : BaseViewModel, ViewModel() {

    final override fun manageActionDisposables(vararg disposable: Disposable) {
        compositeDisposable.addAll(*disposable)
    }

    override fun onCleared() {
        super.onCleared()

        if (!compositeDisposable.isDisposed)
            compositeDisposable.clear()
    }
}