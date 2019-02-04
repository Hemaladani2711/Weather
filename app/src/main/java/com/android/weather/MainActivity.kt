package com.android.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.weather.Objects.Example
import com.android.weather.WebApi.MyEndPoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val TAG:String= "MainActivity";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        downloadData()
    }

    fun downloadData(){
        var apiService:MyEndPoints= MyEndPoints.create()
        var mCallGetData:Call<Example> = apiService.getCurrentWeatherData()
        mCallGetData.enqueue(object : Callback<Example?> {
            override fun onFailure(call: Call<Example?>, t: Throwable) {
                Log.d(TAG,t.message);
            }

            override fun onResponse(call: Call<Example?>, response: Response<Example?>) {
                Log.d(TAG,response.message());
                var ExampleData: Example?=response.body()
                //Log.d(TAG,ExampleData?.timezone.toString());

            }
        } );

    }



}
