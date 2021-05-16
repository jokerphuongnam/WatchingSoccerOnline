package com.pnam.watchingsocceronline.model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Match(
    var mid: Long,
    var firstTeam: Team,
    var secondTeam: Team,
    var vid: Long
) : Parcelable