package com.android.weather.WebApi

import com.android.weather.Objects.Example
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface MyEndPoints{

    companion object {
        val BASE_URL:String="https://api.darksky.net/forecast/db3097e8db050daf08b543c5fee1e601/"
        var longerTimeoutClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
        var gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        var retrofitApiInstance = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(longerTimeoutClient)
            .build()
        fun create(): MyEndPoints{

            return retrofitApiInstance.create(MyEndPoints::class.java)
        }
    }


    @GET("37.8267,-122.4233?exclude=minutely,hourly,daily,alerts,flags")
    fun getCurrentWeatherData():  Call<Example>
}