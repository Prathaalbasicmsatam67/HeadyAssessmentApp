package com.pratham.headytest.ui.home

import com.pratham.headytest.app.UseCase
import com.pratham.headytest.db.repository.RankingRepository
import com.pratham.headytest.db.tables.RankingTable
import com.pratham.headytest.rx.IoThreadScheduler
import com.pratham.headytest.rx.MainThreadScheduler
import com.pratham.headytest.rx.SchedulerProvider
import com.pratham.headytest.ui.home.model.RankingUiModel
import io.reactivex.Flowable
import javax.inject.Inject


class GetAllRankingUseCase @Inject constructor(
    private val repository: RankingRepository,
    @IoThreadScheduler subscribeOnScheduler: SchedulerProvider,
    @MainThreadScheduler observeOnScheduler: SchedulerProvider
) : UseCase<String, List<RankingUiModel>>(subscribeOnScheduler, observeOnScheduler) {


    override fun createObservable(request: String): Flowable<List<RankingUiModel>> {
        return repository.getAllRankings()
            .map { t ->
                t.map { response: RankingTable ->
                    RankingUiModel(response.name, response.id)
                }
            }
    } //close method

}