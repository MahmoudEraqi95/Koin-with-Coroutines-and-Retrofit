package com.eraqi.siatask.data.remote

import com.eraqi.siatask.data.model.StackExchangeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StackExcahngeApi {

    @GET("search") suspend fun search(
        @Query("tagged") tag: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page_size") pageSize:String,
        @Query("site") siteName:String
    ): Response<StackExchangeResponse>

}