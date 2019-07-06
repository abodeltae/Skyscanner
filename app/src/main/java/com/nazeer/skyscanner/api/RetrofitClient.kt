package com.nazeer.skyscanner.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://partners.api.skyscanner.net/"
        private const val API_KEY = "ss630745725358065467897349852985"
    }

    fun getSkyScannerService(): SkyScannerService {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SkyScannerService::class.java)
    }

    private fun getHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val oldRequest = chain.request()
            val url = oldRequest.url().newBuilder().addQueryParameter("apikey", API_KEY).build()
            val newRequest = oldRequest.newBuilder().url(url).build()
            chain.proceed(newRequest)
        }.build()
        return client
    }


}