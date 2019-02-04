package com.android.weather.Objects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Currently {

    @SerializedName("time")
    @Expose
    var time: Long = 0
    @SerializedName("summary")
    @Expose
    var summary: String? = null
    @SerializedName("icon")
    @Expose
    var icon: String? = null
    @SerializedName("nearestStormDistance")
    @Expose
    var nearestStormDistance: Long = 0
    @SerializedName("nearestStormBearing")
    @Expose
    var nearestStormBearing: Long = 0
    @SerializedName("precipIntensity")
    @Expose
    var precipIntensity: Long = 0
    @SerializedName("precipProbability")
    @Expose
    var precipProbability: Long = 0
    @SerializedName("temperature")
    @Expose
    var temperature: Double = 0.toDouble()
    @SerializedName("apparentTemperature")
    @Expose
    var apparentTemperature: Double = 0.toDouble()
    @SerializedName("dewPoint")
    @Expose
    var dewPoint: Double = 0.toDouble()
    @SerializedName("humidity")
    @Expose
    var humidity: Double = 0.toDouble()
    @SerializedName("pressure")
    @Expose
    var pressure: Double = 0.toDouble()
    @SerializedName("windSpeed")
    @Expose
    var windSpeed: Double = 0.toDouble()
    @SerializedName("windGust")
    @Expose
    var windGust: Double = 0.toDouble()
    @SerializedName("windBearing")
    @Expose
    var windBearing: Long = 0
    @SerializedName("cloudCover")
    @Expose
    var cloudCover: Double = 0.toDouble()
    @SerializedName("uvIndex")
    @Expose
    var uvIndex: Long = 0
    @SerializedName("visibility")
    @Expose
    var visibility: Double = 0.toDouble()
    @SerializedName("ozone")
    @Expose
    var ozone: Double = 0.toDouble()

}