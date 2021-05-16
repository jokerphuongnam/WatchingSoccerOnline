package com.pnam.watchingsocceronline.model.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.model.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Comment(
    var cid: Long,
    var uid: Long,
    var vid: Long,
    var content: String,
    var time: Long
) : Parcelable {
    var showTimeDate: String
        get() = time.toDateTimeString
        set(value) {
            val dateFormat = SimpleDateFormat("hh:mm dd/MM/yyyy", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            time = dateFormat.parse(value)!!.time
        }
}