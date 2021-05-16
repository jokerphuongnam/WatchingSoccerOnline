package com.pnam.watchingsocceronline.model.util

import java.text.SimpleDateFormat
import java.util.*

val Long.toDateTimeString: String
    get() {
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getDefault()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(calendar.time)
    }