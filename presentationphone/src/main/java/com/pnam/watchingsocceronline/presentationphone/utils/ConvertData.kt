package com.pnam.watchingsocceronline.presentationphone.utils

import androidx.annotation.StringRes
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.R

fun Video.toSearch(): SearchHistory = SearchHistory(
    vid,
    title,
    0,
    SearchHistory.SearchType.SUGGESTION
)

@StringRes
fun User.Gender.toStringRes(): Int = when (this) {
    User.Gender.MALE -> R.string.male
    User.Gender.FEMALE -> R.string.female
}