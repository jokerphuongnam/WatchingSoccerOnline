package com.pnam.watchingsocceronline.domain.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.domain.util.HH_MM_DD_MM_YYYY
import com.pnam.watchingsocceronline.domain.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class SearchHistory(
    var sid: Long = 0,
    var searchWord: String,
    var searchTime: Long,
    var searchType: SearchType
) : Parcelable {
    var showTimeDate: String
        get() = searchTime.toDateTimeString(HH_MM_DD_MM_YYYY)
        set(value) {
            val dateFormat = SimpleDateFormat(HH_MM_DD_MM_YYYY, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            searchTime = dateFormat.parse(value)!!.time
        }

    enum class SearchType {
        HISTORY, SUGGESTION
    }
}