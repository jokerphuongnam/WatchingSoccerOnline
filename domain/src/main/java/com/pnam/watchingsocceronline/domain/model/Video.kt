package com.pnam.watchingsocceronline.domain.model

import com.pnam.watchingsocceronline.domain.utils.toDateTimeString
import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.Locale

data class Video(
    var vid: Long,
    var title: String,
    var thumbnail: String,
    var video: String,
    var view: Long,
    var showTime: Long
) {
    var showTimeDate: String
        get() = showTime.toDateTimeString
        set(value) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            showTime = dateFormat.parse(value)!!.time
        }
}