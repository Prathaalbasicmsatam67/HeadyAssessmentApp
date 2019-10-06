package com.pratham.headytest.ui.home

import com.pratham.headytest.services.ApiService
import com.pratham.headytest.ui.home.model.HeadyDataApiResponse
import io.reactivex.Flowable
import javax.inject.Inject

class HeadyDataRepository @Inject constructor(private val apiService: ApiService) {

    fun getAllApiData(): Flowable<HeadyDataApiResponse> {
        return apiService.getHeadyData();
    }
}