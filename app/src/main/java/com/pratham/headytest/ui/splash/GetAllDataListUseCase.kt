package com.pratham.headytest.ui.splash

import com.pratham.headytest.app.UseCase
import com.pratham.headytest.db.repository.HeadyDataRepository
import com.pratham.headytest.rx.IoThreadScheduler
import com.pratham.headytest.rx.MainThreadScheduler
import com.pratham.headytest.rx.SchedulerProvider
import com.pratham.headytest.ui.model.HeadyDataApiResponse
import com.pratham.headytest.ui.model.HeadyDataUiModel
import io.reactivex.Flowable
import javax.inject.Inject

class GetAllDataListUseCase @Inject constructor(
    private val repository: HeadyDataRepository,
    @IoThreadScheduler subscribeOnScheduler: SchedulerProvider,
    @MainThreadScheduler observeOnScheduler: SchedulerProvider
) : UseCase<String, HeadyDataUiModel>(subscribeOnScheduler, observeOnScheduler) {


    override fun createObservable(request: String): Flowable<HeadyDataUiModel> {
        return repository.getAllApiData()
            .map { response: HeadyDataApiResponse ->
                HeadyDataUiModel(response.categories, response.rankings)
            }

    } //close method

}