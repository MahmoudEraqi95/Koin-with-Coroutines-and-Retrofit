package com.eraqi.siatask


/**
 * this class is like a wrapper object to all of our possible results , when calling an api, could be used for other tasks,
 * but in the scope of this app we used it for this
 */
sealed class Result  {
    class Success(val data: Any?) : Result()
    object Loading : Result()
    class Error(val exception: Exception) : Result()
}