package com.pnam.watchingsocceronline.presentationphone.utils

import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.domain.model.Video

fun Video.toSearch(): SearchHistory = SearchHistory(
    vid,
    title,
    0,
    SearchHistory.SearchType.SUGGESTION
)