package com.eraqi.siatask.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eraqi.siatask.Result
import com.eraqi.siatask.domain.repo.StackExchangeRepoImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class ChatbotViewModel(var tag:String,var order:String, var sort:String, var pageSize:String) : ViewModel(), KoinComponent {

    val stackRepo = StackExchangeRepoImpl()



    var resultMutableLiveData = MutableLiveData<Result>()

        init {
            getResults()
        }


      fun getResults(){

        CoroutineScope(IO).launch{
            resultMutableLiveData.postValue(stackRepo.searchRemote(tag, order, sort, pageSize))
        }
      }








}