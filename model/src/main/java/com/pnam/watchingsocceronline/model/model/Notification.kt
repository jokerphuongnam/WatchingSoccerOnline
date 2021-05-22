package com.pnam.watchingsocceronline.model.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.model.util.toCalendar
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class Notification(
    var nid: Long,
    var vid: Long,
    var title: String,
    var thumbnail: String,
    var showTime: Long
) : Parcelable {
    fun nearTimeDate(
        yearLb: String,
        monthLb: String,
        dayLb: String,
        hourLb: String,
        minuteLb: String
    ): String {
        val timeVideoShow: Calendar = showTime.toCalendar
        val currentTime: Calendar = Calendar.getInstance()
        currentTime.timeZone = TimeZone.getDefault()
        val minutes = timeVideoShow.get(Calendar.MINUTE) - currentTime.get(Calendar.MINUTE)
        return if (minutes < 60) {
            "$minutes $minuteLb"
        } else {
            val hour = timeVideoShow.get(Calendar.HOUR) - currentTime.get(Calendar.HOUR)
            if (hour < 24) {
                "$hour $hourLb"
            } else {
                val day =
                    timeVideoShow.get(Calendar.DAY_OF_MONTH) - currentTime.get(Calendar.DAY_OF_MONTH)
                if (day < currentTime.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    "$day $dayLb"
                } else {
                    val month = timeVideoShow.get(Calendar.MONTH) - currentTime.get(Calendar.MONTH)
                    if (month < 12) {
                        "$month $monthLb"
                    } else {
                        val year = timeVideoShow.get(Calendar.YEAR) - currentTime.get(Calendar.YEAR)
                        "$year $yearLb"
                    }
                }
            }
        }
    }
}