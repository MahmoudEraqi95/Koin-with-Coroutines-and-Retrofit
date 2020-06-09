package com.eraqi.siatask


sealed class Result  {
    class Success(val data: Any?) : Result()
    object Loading : Result()
    class Error(val exception: Exception) : Result()
}