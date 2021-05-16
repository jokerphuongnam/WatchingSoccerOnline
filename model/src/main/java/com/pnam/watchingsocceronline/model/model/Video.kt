package com.pnam.watchingsocceronline.model.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.model.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Video(
    var vid: Long,
    var title: String,
    var thumbnail: String,
    var video: String,
    var view: Long,
    var showTime: Long,
    var comments: MutableList<Comment>,
    var match: Match
) : Parcelable {
    var showTimeDate: String
        get() = showTime.toDateTimeString
        set(value) {
            val dateFormat = SimpleDateFormat("hh:mm dd/MM/yyyy", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            showTime = dateFormat.parse(value)!!.time
        }
}