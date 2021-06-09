package com.pnam.watchingsocceronline.domain.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.domain.util.DD_MM_YYYY
import com.pnam.watchingsocceronline.domain.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class User(
    var uid: String,
    var avatar: String,
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var birthDay: Long,
    var gender: Gender
) : Parcelable {

    constructor() : this("", "N/A", "", "", "", "", 916678800000, Gender.MALE)

    var showBirthDay: String
        get() = birthDay.toDateTimeString(DD_MM_YYYY)
        set(value) {
            val dateFormat = SimpleDateFormat(DD_MM_YYYY, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            birthDay = dateFormat.parse(value)!!.time
        }

    enum class Gender {
        MALE {
            override fun toBoolean(): Boolean {
                return false
            }
        },
        FEMALE {
            override fun toBoolean(): Boolean {
                return true
            }
        };

        abstract fun toBoolean(): Boolean
    }
}