package com.pratham.headytest.ui.categoryfilter

import com.pratham.headytest.app.UseCase
import com.pratham.headytest.db.repository.CategoryRepository
import com.pratham.headytest.db.tables.CategoryTable
import com.pratham.headytest.rx.IoThreadScheduler
import com.pratham.headytest.rx.MainThreadScheduler
import com.pratham.headytest.rx.SchedulerProvider
import com.pratham.headytest.ui.categoryfilter.model.CategoryUiModel
import io.reactivex.Flowable
import javax.inject.Inject


class GetAllCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository,
    @IoThreadScheduler subscribeOnScheduler: SchedulerProvider,
    @MainThreadScheduler observeOnScheduler: SchedulerProvider
) : UseCase<String, List<CategoryUiModel>>(subscribeOnScheduler, observeOnScheduler) {


    override fun createObservable(request: String): Flowable<List<CategoryUiModel>> {
        return repository.getAllCategories()
            .map { t ->
                t.map { response: CategoryTable ->
                    CategoryUiModel(response.name, response.server_id, response.id)
                }
            }

    } //close method

}