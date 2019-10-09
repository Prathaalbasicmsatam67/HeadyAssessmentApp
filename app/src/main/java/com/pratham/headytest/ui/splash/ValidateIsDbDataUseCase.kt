package com.pratham.headytest.ui.splash

import com.pratham.headytest.app.UseCase
import com.pratham.headytest.db.repository.ProductRepository
import com.pratham.headytest.db.tables.ProductTable
import com.pratham.headytest.rx.IoThreadScheduler
import com.pratham.headytest.rx.MainThreadScheduler
import com.pratham.headytest.rx.SchedulerProvider
import com.pratham.headytest.ui.model.ProductUiModel
import io.reactivex.Flowable
import javax.inject.Inject

class ValidateIsDbDataUseCase @Inject constructor(
    private val repository: ProductRepository,
    @IoThreadScheduler subscribeOnScheduler: SchedulerProvider,
    @MainThreadScheduler observeOnScheduler: SchedulerProvider
) : UseCase<String, List<ProductUiModel>>(subscribeOnScheduler, observeOnScheduler) {


    override fun createObservable(request: String): Flowable<List<ProductUiModel>> {
        return repository.getAllProduct().map { data ->
            data.asSequence().map { tableData: ProductTable ->
                ProductUiModel(
                    tableData.id, tableData.name, tableData.server_id,
                    null
                )
            }.toList()
        }
    }


}