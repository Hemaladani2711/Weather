package com.android.weather

import android.content.Context
import android.graphics.drawable.Drawable

/*
* icon values::
* clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy,
* partly-cloudy-day, or partly-cloudy-night, hail, thunderstorm, or tornado
*
* https://darksky.net/dev/docs#api-request-types
**/
class ImageHelper (val icon:String?,val mContext:Context) {

    var mLinkedImages=HashMap<String,String>();

    init{
        //map to link icon names to images
        mLinkedImages= hashMapOf("clear-day" to "clear_day","clear-night" to "clear_night","rain" to "rain","snow" to "snow","sleet" to "sleet","wind" to "wind","fog" to "fog","cloudy" to "cloud","partly-cloudy-day" to "partly_cloudy_day","partly-cloudy-night" to "partly_cloudy_night","hail" to "thunder_storm","thunderstorm" to "thunder_storm","tornado" to "tornado")

    }

    fun getSummaryImage():Drawable
    {
        val image=mLinkedImages.get(icon)
        return mContext.resources.getDrawable(mContext.resources.getIdentifier(image,"drawable",mContext.packageName))
    }
}