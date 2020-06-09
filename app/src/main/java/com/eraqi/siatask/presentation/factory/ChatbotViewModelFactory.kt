package com.eraqi.siatask.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eraqi.siatask.presentation.ChatbotViewModel

class ChatbotViewModelFactory(var tag:String, var order:String, var sort:String, var pageSize:String):ViewModelProvider.Factory {



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatbotViewModel(tag, order, sort, pageSize) as T
    }
}