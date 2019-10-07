package com.pratham.headytest.ui.splash

import com.pratham.headytest.app.UseCase
import com.pratham.headytest.db.repository.HeadyDataRepository
import com.pratham.headytest.rx.IoThreadScheduler
import com.pratham.headytest.rx.MainThreadScheduler
import com.pratham.headytest.rx.SchedulerProvider
import com.pratham.headytest.ui.model.HeadyDataUiModel
import io.reactivex.Flowable
import javax.inject.Inject

class StoreHeadyDataUseCase @Inject constructor(
    private val repository: HeadyDataRepository,
    @IoThreadScheduler subscribeOnScheduler: SchedulerProvider,
    @MainThreadScheduler observeOnScheduler: SchedulerProvider
) : UseCase<HeadyDataUiModel, Boolean>(subscribeOnScheduler, observeOnScheduler) {


    override fun createObservable(request: HeadyDataUiModel): Flowable<Boolean> {
        return repository.storeHeadyData(request)


    } //close method

}