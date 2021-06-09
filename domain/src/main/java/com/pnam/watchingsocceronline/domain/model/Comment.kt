package com.pnam.watchingsocceronline.domain.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.domain.util.HH_MM_DD_MM_YYYY
import com.pnam.watchingsocceronline.domain.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Comment(
    var cid: String,
    var content: String,
    var time: Long,
    var avatar: String,
    var firstName: String,
    var lastName: String,
) : Parcelable {
    var showTimeDate: String
        get() = time.toDateTimeString(HH_MM_DD_MM_YYYY)
        set(value) {
            val dateFormat = SimpleDateFormat(HH_MM_DD_MM_YYYY, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            time = dateFormat.parse(value)!!.time
        }
}