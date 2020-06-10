package com.eraqi.siatask

import com.eraqi.siatask.Result
import com.eraqi.siatask.data.model.StackExchangeResponse

import com.eraqi.siatask.domain.repo.StackExchangeRepoImpl
import junit.framework.Assert.assertEquals

import junit.framework.Assert.assertSame
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock


@RunWith(JUnit4::class)
class StackExchangeRepoTest {


    lateinit var stackExhangeRepo:StackExchangeRepoImpl
     var stackExchangeResponse: StackExchangeResponse? = null
    @Before
    fun setUp(){

        stackExhangeRepo = StackExchangeRepoImpl()
    }

    @Test
     fun TestDataSuccessfulyRetrieved(){

        val actualResult = runBlocking {
            println((stackExhangeRepo.searchRemote("testing","asc", "activity", "10")as Result.Error).exception)}
        assertSame(Result.Success(stackExchangeResponse), actualResult )

    }

}