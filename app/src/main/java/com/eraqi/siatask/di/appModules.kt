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

val module = module{

    single { createOkHttpClientInstancee() }
    single { createRetrofitClientInstance(get(), Constants.BASE_URL) }
    single { creatStackExchangeApiInstance(get())}


}
fun createOkHttpClientInstancee(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofitClientInstance(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun creatStackExchangeApiInstance(retrofit: Retrofit): StackExcahngeApi {
    return retrofit.create(StackExcahngeApi::class.java)

}