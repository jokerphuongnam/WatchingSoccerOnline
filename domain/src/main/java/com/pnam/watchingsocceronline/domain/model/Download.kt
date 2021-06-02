package com.pnam.watchingsocceronline.domain.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.domain.util.DateUtils
import com.pnam.watchingsocceronline.domain.util.toCalendar
import com.pnam.watchingsocceronline.domain.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Download(
    var vid: String,
    var title: String,
    var thumbnail: String,
    var url: String,
    var view: Long,
    var downloadTime: Long,
    var downloadProcess: Int
) : Parcelable {
    var showTimeDate: String
        get() = downloadTime.toDateTimeString(DateUtils.HH_MM_DD_MM_YYYY)
        set(value) {
            val dateFormat = SimpleDateFormat(DateUtils.HH_MM_DD_MM_YYYY, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            downloadTime = dateFormat.parse(value)!!.time
        }

    fun nearTimeDate(
        due: String,
        yearLb: String,
        monthLb: String,
        dayLb: String,
        hourLb: String,
        minuteLb: String
    ): String {
        val timeVideoShow: Calendar = downloadTime.toCalendar
        val currentTime: Calendar = Calendar.getInstance()
        currentTime.timeZone = TimeZone.getDefault()
        return "$due " + if (timeVideoShow.timeInMillis < currentTime.timeInMillis) {
            downloadTime.toDateTimeString(DateUtils.HH_MM_DD_MM_YYYY)
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