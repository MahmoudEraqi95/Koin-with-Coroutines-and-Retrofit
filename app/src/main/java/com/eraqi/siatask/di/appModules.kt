package com.eraqi.siatask.di



import com.eraqi.siatask.data.Constants
import com.eraqi.siatask.data.remote.StackExcahngeApi
import com.eraqi.siatask.data.repo.StackExhangeRepo
import com.eraqi.siatask.domain.repo.StackExchangeRepoImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 *this kotlin file is responsible for creating object that gets used alot us using Koin Library
 */
val module = module{

    single { createOkHttpClientInstancee() }
    single { createRetrofitClientInstance(get(), Constants.BASE_URL) }
    single { creatStackExchangeApiInstance(get())}


}

/**
*this function creates an instance of the OkHttpclient to handle the timeout reading, writing,and connecting time, and logs all the details
 * about requests
 */
fun createOkHttpClientInstancee(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

/**
 *this function creates an instance of the Retrofit to send requests from a specific api
 *@param okHttpClient: we get from the Koin di library
 * @param BASE_URL : the endpoint we are making the request to
 * @return retrofit instance
 */
fun createRetrofitClientInstance(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

/**
 *this function creates a an api which we can use directly to make a request to our endpoint
 * @param retrofit
 * @return StackExchangeApi instance
 */
fun creatStackExchangeApiInstance(retrofit: Retrofit): StackExcahngeApi {
    return retrofit.create(StackExcahngeApi::class.java)

}