package com.eraqi.siatask.data.remote

import com.eraqi.siatask.data.model.StackExchangeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
/**
 *this interface contains the endpoints of the api we 're making requests to.
 */
interface StackExcahngeApi {

    /**
     *this function creates is responsible for taking the paramaters that is retrieved from the chat between the user and the chatbot
     * to retrieve the required data
     */
    @GET("search") suspend fun search(
        @Query("tagged") tag: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("pagesize") pageSize:String,
        @Query("site") siteName:String
    ): Response<StackExchangeResponse>

}