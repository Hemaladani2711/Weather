package com.android.weather

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.weather.Objects.Example
import com.android.weather.WebApi.MyEndPoints
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    val TAG:String= "MainActivity"
    val ACCESS_COURSE_LOCATION_CODE:Int=1
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions( this,  arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),ACCESS_COURSE_LOCATION_CODE);
        }else{
            permissionGranted()
        }
    }

    //Once Permissions are approved..
    @SuppressLint("MissingPermission")
    fun permissionGranted(){
        val addOnSuccessListener = fusedLocationClient.lastLocation.addOnSuccessListener {location:Location?->
            Log.d(TAG, "Latitude:" + location?.latitude + " ,Longitude:" + location?.longitude)
            if (location != null) {
                downloadData(location?.latitude, location?.longitude)
            }

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            ACCESS_COURSE_LOCATION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    permissionGranted()
                } else {
                    //todo handle permission denied
                }
                return
            }
        }
    }

    /*
    * Sample output:
    * https://api.darksky.net/forecast/0123456789abcdef9876543210fedcba/42.3601,-71.0589,255657600?exclude=minutely,hourly,daily,alerts,flags
    *{
        "latitude": 42.3601,
        "longitude": -71.0589,
        "timezone": "America/New_York",
        "currently": {
            "time": 255657600,
            "summary": "Heavy Snow and Dangerously Windy",
            "icon": "snow",
            "precipIntensity": 0.1692,
            "precipProbability": 1,
            "precipType": "snow",
            "temperature": 30.38,
            "apparentTemperature": 13.49,
            "dewPoint": 29.24,
            "humidity": 0.95,
            "pressure": 1006.67,
            "windSpeed": 40.36,
            "windGust": 83.48,
            "windBearing": 63,
            "cloudCover": 1,
            "uvIndex": 0,
            "visibility": 0.2
        },
        "offset": -5
    }
    * */

    /*-----Sample Lat Longs
    * Stockholm, Sweden: 59.328313, 18.074098
    * London, UK: 51.507103, -0.129768
    * Sydney, AU: -33.822453, 150.960778
    * */

    fun downloadData(latitude:Double?,longitude:Double?){
        var lat:Double?;var long:Double?;
        lat=latitude;long=longitude

        //todo comment below line. Its for testing only
        lat=-33.822453;long=150.960778

        var apiService:MyEndPoints= MyEndPoints.create()
        var mCallGetData:Call<Example> = apiService.getCurrentWeatherData(lat,long)
        mCallGetData.enqueue(object : Callback<Example?> {
            override fun onFailure(call: Call<Example?>, t: Throwable) {
                Log.d(TAG,"onFailure"+t.message);
                //todo handle failure

            }

            override fun onResponse(call: Call<Example?>, response: Response<Example?>) {
                Log.d(TAG,"onResponse:"+response.message()+"res:");
                val address = Geocoder(applicationContext, Locale.getDefault()).getFromLocation(lat!!, long!!, 1).get(0)
                txtCity.text=address.locality
                var ExampleData: Example?=response.body()
                txtSummary.text=ExampleData?.currently?.summary
                txtTemperature.text=ExampleData?.currently?.temperature.toString()
                imgSummary.setImageDrawable(ImageHelper(ExampleData?.currently?.icon,applicationContext).getSummaryImage())
                Log.d(TAG,ExampleData?.timezone.toString());

            }
        } );

    }



}
