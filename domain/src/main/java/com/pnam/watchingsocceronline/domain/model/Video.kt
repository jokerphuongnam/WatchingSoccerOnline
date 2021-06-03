package com.pnam.watchingsocceronline.domain.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.domain.util.DateUtils
import com.pnam.watchingsocceronline.domain.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Video(
    var vid: Long,
    var title: String,
    var thumbnail: String,
    var url: String,
    var view: Long,
    var date: Long,
    var highLight1: String,
    var highLight2: String,
    var highLight3: String,
    var comments: MutableList<Comment>
) : Parcelable {
    var showTimeDate: String
        get() = date.toDateTimeString(DateUtils.HH_MM_DD_MM_YYYY)
        set(value) {
            val dateFormat = SimpleDateFormat(DateUtils.HH_MM_DD_MM_YYYY, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            date = dateFormat.parse(value)!!.time
        }
}