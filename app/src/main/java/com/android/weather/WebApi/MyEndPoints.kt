package com.android.weather.WebApi

import com.android.weather.Objects.Example
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

//Api documentation can be found at: https://darksky.net/dev/docs#api-request-types


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


    @GET("{latitude}"+","+"{longitude}"+"?exclude=minutely,hourly,daily,alerts,flags")
    fun getCurrentWeatherData(@Path("latitude") latitude:Double?, @Path("longitude")longitude:Double?):  Call<Example>
}