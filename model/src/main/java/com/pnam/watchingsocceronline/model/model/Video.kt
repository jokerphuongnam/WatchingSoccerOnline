package com.pnam.watchingsocceronline.model.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.model.util.DateUtils
import com.pnam.watchingsocceronline.model.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Video(
    var vid: String,
    var title: String,
    var thumbnail: String,
    var url: String,
    var view: Long,
    var showTime: Long,
    var highLight1: String,
    var highLight2: String,
    var highLight3: String,
    var comments: MutableList<Comment>,
    var match: Match
) : Parcelable {
    var showTimeDate: String
        get() = showTime.toDateTimeString(DateUtils.HH_MM_DD_MM_YYYY)
        set(value) {
            val dateFormat = SimpleDateFormat(DateUtils.HH_MM_DD_MM_YYYY, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            showTime = dateFormat.parse(value)!!.time
        }
}