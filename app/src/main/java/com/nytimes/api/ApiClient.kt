package com.nytimes.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/"
    private var retrofit: Retrofit? = null
    val client: Retrofit?
        get() {
            if (com.nytimes.api.ApiClient.retrofit == null) {
                com.nytimes.api.ApiClient.retrofit = Retrofit.Builder()
                    .client(OkHttpClient.Builder().build())
                    .baseUrl(com.nytimes.api.ApiClient.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return com.nytimes.api.ApiClient.retrofit
        }
}