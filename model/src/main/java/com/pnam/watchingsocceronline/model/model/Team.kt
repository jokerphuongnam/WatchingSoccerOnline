package com.pnam.watchingsocceronline.model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Team(
    var tid: Long,
    var teamName: String,
    var goals: Int
) : Parcelable