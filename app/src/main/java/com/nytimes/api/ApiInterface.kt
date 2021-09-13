package com.nytimes.api

import com.nytimes.R
import com.nytimes.pojo.MostViewedResponse
import retrofit2.Call
import retrofit2.http.GET


//import static android.provider.Settings.System.getString;

//import static android.provider.Settings.System.getString;
interface ApiInterface {
    @get:GET("viewed/7.json?api-key=VADRFOOxKcytqSAj9OpzLzdQPDo3x9Fn")
    val mostViewedList: Call<MostViewedResponse?>?
}