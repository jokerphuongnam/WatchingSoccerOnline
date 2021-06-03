package com.pnam.watchingsocceronline.domain.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.domain.util.DateUtils
import com.pnam.watchingsocceronline.domain.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class User(
    var uid: Long,
    var avatar: String,
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var birthDay: Long,
    var gender: Gender
) : Parcelable {
    var showBirthDay: String
        get() = birthDay.toDateTimeString(DateUtils.DD_MM_YYYY)
        set(value) {
            val dateFormat = SimpleDateFormat(DateUtils.DD_MM_YYYY, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            birthDay = dateFormat.parse(value)!!.time
        }

    enum class Gender {
        MALE, FEMALE;

        fun toBoolean(): Boolean = when(this){
            MALE ->{
                false
            }
            FEMALE->{
                true
            }
        }
    }
}