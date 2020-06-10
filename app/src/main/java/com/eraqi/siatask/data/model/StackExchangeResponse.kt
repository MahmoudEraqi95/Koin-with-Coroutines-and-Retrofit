package com.eraqi.siatask.data.model

import com.google.gson.annotations.SerializedName
/**
*this file contains the structure of the response of the request from stckexchange ,
 * it has 3 inner classes which are all json objects and arrays in the response
 */
data class StackExchangeResponse(var items:List<Item>)
data class Item(var tags:List<String>, var title:String, var owner:Owner, var link:String)
data class Owner(@SerializedName("display_name") var displayName:String)

