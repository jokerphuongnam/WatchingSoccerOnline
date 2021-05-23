package com.pnam.watchingsocceronline.model.model

import android.os.Parcelable
import com.pnam.watchingsocceronline.model.util.toDateTimeString
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class SearchHistory(
    var sid: Long,
    var uid: Long,
    var searchWord: String,
    var searchTime: Long,
    var searchType: SearchType
) : Parcelable {
    var showTimeDate: String
        get() = searchTime.toDateTimeString
        set(value) {
            val dateFormat = SimpleDateFormat("hh:mm dd/MM/yyyy", Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            searchTime = dateFormat.parse(value)!!.time
        }

    enum class SearchType {
        HISTORY, SUGGESTION
    }
}