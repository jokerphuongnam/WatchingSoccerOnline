package com.pnam.watchingsocceronline.model.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.model.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class User(
    var uid: Long,
    var avatar: String,
    var firstName: String,
    var lastName: String,
    var birthDay: Long
) : Parcelable {
    var showBirthDay: String
        get() = birthDay.toDateTimeString
        set(value) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            birthDay = dateFormat.parse(value)!!.time
        }
}