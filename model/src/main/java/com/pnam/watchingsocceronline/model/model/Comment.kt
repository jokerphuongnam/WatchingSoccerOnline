package com.pnam.watchingsocceronline.model.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.model.util.DateUtils
import com.pnam.watchingsocceronline.model.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Comment(
    var cid: Long,
    var content: String,
    var time: Long,
    var user: User
) : Parcelable {
    var showTimeDate: String
        get() = time.toDateTimeString(DateUtils.HH_MM_DD_MM_YYYY)
        set(value) {
            val dateFormat = SimpleDateFormat(DateUtils.HH_MM_DD_MM_YYYY, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            time = dateFormat.parse(value)!!.time
        }
}