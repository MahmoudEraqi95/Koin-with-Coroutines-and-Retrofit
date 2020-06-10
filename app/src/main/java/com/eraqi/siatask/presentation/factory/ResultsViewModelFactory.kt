package com.eraqi.siatask.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eraqi.siatask.presentation.ResultsViewModel


/**
 * this class just handles the passing data to the constructor of the view model, as we arenot supposed to pass it directly to the view model
 * to reduce coubling
 */
class ResultsViewModelFactory(var tag:String, var order:String, var sort:String, var pageSize:String):ViewModelProvider.Factory {



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResultsViewModel(tag, order, sort, pageSize) as T
    }
}