package com.pnam.watchingsocceronline.model.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.model.util.DateUtils
import com.pnam.watchingsocceronline.model.util.toDateTimeString
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
        get() = searchTime.toDateTimeString(DateUtils.HH_MM_DD_MM_YYYY)
        set(value) {
            val dateFormat = SimpleDateFormat(DateUtils.HH_MM_DD_MM_YYYY, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            searchTime = dateFormat.parse(value)!!.time
        }

    enum class SearchType {
        HISTORY, SUGGESTION
    }
}