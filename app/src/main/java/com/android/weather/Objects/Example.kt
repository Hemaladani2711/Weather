package com.android.weather.Objects

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example {

    @SerializedName("latitude")
    @Expose
    var latitude: Double = 0.toDouble()
    @SerializedName("longitude")
    @Expose
    var longitude: Double = 0.toDouble()
    @SerializedName("timezone")
    @Expose
    var timezone: String? = null
    @SerializedName("currently")
    @Expose
    var currently: Currently? = null
    @SerializedName("offset")
    @Expose
    var offset: Long = 0

}