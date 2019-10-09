package com.pratham.headytest.db.repository

import com.pratham.headytest.db.dao.RankingDao
import com.pratham.headytest.db.tables.RankingTable
import io.reactivex.Flowable
import javax.inject.Inject


class RankingRepository @Inject constructor(
    private val rankingDao: RankingDao
) {


    fun getAllRankings(): Flowable<List<RankingTable>> {
        return Flowable.just(rankingDao.allRankings());
    }

}