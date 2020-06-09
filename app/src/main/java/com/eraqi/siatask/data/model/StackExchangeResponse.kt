package com.eraqi.siatask.data.model

import com.google.gson.annotations.SerializedName

data class StackExchangeResponse(var items:List<Item>)
data class Item(var tags:List<String>, var title:String, var owner:Owner, var link:String)
data class Owner(@SerializedName("display_name") var displayName:String)

