package com.pnam.watchingsocceronline.domain.util

import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video
import java.text.SimpleDateFormat
import java.util.*

val Long.toCalendar: Calendar
    get() {
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getDefault()
        calendar.timeInMillis = this
        return calendar
    }

fun Long.toDateTimeString(pattern: String): String {
    val calendar: Calendar = toCalendar
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(calendar.time)
}

fun Boolean.toGender(): User.Gender = if (this) {
    User.Gender.FEMALE
} else {
    User.Gender.MALE
}