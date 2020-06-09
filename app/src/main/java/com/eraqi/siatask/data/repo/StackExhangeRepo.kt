package com.eraqi.siatask.data.repo

import com.eraqi.siatask.Result
import com.eraqi.siatask.data.model.StackExchangeResponse

interface StackExhangeRepo {

   suspend fun searchRemote(tag:String, order:String, sort:String, pageSize:String):Result

}