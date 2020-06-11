package com.eraqi.siatask.data.repo

import com.eraqi.siatask.Result


interface StackExchangeRepo {

   suspend fun searchRemote(tag:String, order:String, sort:String, pageSize:String):Result

}