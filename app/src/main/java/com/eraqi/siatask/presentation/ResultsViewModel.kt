package com.eraqi.siatask.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eraqi.siatask.Result
import com.eraqi.siatask.domain.repo.StackExchangeRepoImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent


/**
 * this class is responsible fetching the data from repository, whether it's from local database or from server and present it to
 * the ResultFragment class by making the ResultFragment class observes the resultMutableLiveData object
 * it also handles the lifecycle of the activity
 */
class ResultsViewModel(var tag:String, var order:String, var sort:String, var pageSize:String) : ViewModel(), KoinComponent {

    val stackRepo = StackExchangeRepoImpl()



    var resultMutableLiveData:MutableLiveData<Result>

        init {
            resultMutableLiveData = MutableLiveData<Result>()
            getResults()

        }


      fun getResults(){

        CoroutineScope(IO).launch{
            resultMutableLiveData.postValue(stackRepo.searchRemote(tag, order, sort, pageSize))
        }

      }








}