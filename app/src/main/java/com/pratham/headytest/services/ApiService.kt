package com.pratham.headytest.services

import com.pratham.headytest.ui.model.HeadyDataApiResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers(
        "Accept: application/json",
        "Content-type:application/json"
    )
    @GET(
        "/json"
    )
    fun getHeadyData(): Flowable<HeadyDataApiResponse>
}