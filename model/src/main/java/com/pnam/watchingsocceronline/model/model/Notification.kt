package com.pnam.watchingsocceronline.model.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.model.util.DateUtils
import com.pnam.watchingsocceronline.model.util.toCalendar
import com.pnam.watchingsocceronline.model.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class Notification(
    var nid: Long,
    var vid: String,
    var title: String,
    var thumbnail: String,
    var showTime: Long
) : Parcelable {
    fun nearTimeDate(
        due: String,
        yearLb: String,
        monthLb: String,
        dayLb: String,
        hourLb: String,
        minuteLb: String
    ): String {
        val timeVideoShow: Calendar = showTime.toCalendar
        val currentTime: Calendar = Calendar.getInstance()
        currentTime.timeZone = TimeZone.getDefault()
        return if (timeVideoShow.timeInMillis < currentTime.timeInMillis) {
            showTime.toDateTimeString(DateUtils.HH_MM_DD_MM_YYYY)
        } else {
            val year =
                timeVideoShow.get(Calendar.YEAR) - currentTime.get(Calendar.YEAR)
            if (year > 0) {
                "$year $yearLb"
            } else {
                val month =
                    timeVideoShow.get(Calendar.MONTH) - currentTime.get(Calendar.MONTH)
                if (month > 0) {
                    "$month $monthLb"
                } else {
                    val day =
                        timeVideoShow.get(Calendar.DAY_OF_MONTH) - currentTime.get(Calendar.DAY_OF_MONTH)
                    if (day > 0) {
                        "$day $dayLb"
                    } else {
                        val hour = timeVideoShow.get(Calendar.HOUR) - currentTime.get(Calendar.HOUR)
                        if (hour > 0) {
                            "$hour $hourLb"
                        } else {
                            val minutes =
                                timeVideoShow.get(Calendar.MINUTE) - currentTime.get(Calendar.MINUTE)
                            "$minutes $minuteLb"
                        }
                    }
                }
            }
        }
    }
}