package com.eraqi.siatask.domain.repo

import com.eraqi.siatask.Result

import com.eraqi.siatask.data.model.StackExchangeResponse
import com.eraqi.siatask.data.remote.StackExcahngeApi
import com.eraqi.siatask.data.repo.StackExhangeRepo

import org.koin.core.KoinComponent
import org.koin.core.get
import java.io.IOError
import java.io.IOException


/**
 * this class is responsible for fetching the data from the datasource which could be a local database or remote data
 * in our case it's remote data so it calls the api using retrofit and sends the data to the viewmodel class
 */
class StackExchangeRepoImpl:StackExhangeRepo, KoinComponent {
    override suspend fun searchRemote(
        tag: String,
        order: String,
        sort: String,
        pageSize: String
    ): Result {
        try {
            val stackExcahngeApi:StackExcahngeApi = get()
            val searchResult  = stackExcahngeApi.search(tag, sort, order, pageSize, "stackoverflow")
            if(searchResult.isSuccessful){
                return Result.Success(searchResult.body())
            }
        }catch (e:Exception){
            return Result.Error(e)
        }
        return Result.Error(IOException("Error occured while trying to fetch results"))



    }

}