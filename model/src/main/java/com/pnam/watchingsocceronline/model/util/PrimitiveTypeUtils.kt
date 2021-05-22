package com.pnam.watchingsocceronline.model.util

import java.text.SimpleDateFormat
import java.util.*

val Long.toCalendar: Calendar
    get() {
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getDefault()
        calendar.timeInMillis = this
        return calendar
    }

val Long.toDateTimeString: String
    get() {
        val calendar: Calendar = toCalendar
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(calendar.time)
    }