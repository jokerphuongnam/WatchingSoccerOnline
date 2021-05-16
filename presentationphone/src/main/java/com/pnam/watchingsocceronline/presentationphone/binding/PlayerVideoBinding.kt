package com.pnam.watchingsocceronline.presentationphone.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.ui.PlayerView
import com.pnam.watchingsocceronline.model.model.SearchHistory
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.extensionview.simpleExoPlayer
import com.pnam.watchingsocceronline.presentationphone.extensionview.url

@BindingAdapter("url")
fun setUrl(playerView: PlayerView, url: String) {
    playerView.url = url
    playerView.player = playerView.simpleExoPlayer
}

@BindingAdapter("search_type")
fun historyOrSearch(imageView: ImageView, searchType: SearchHistory.SearchType){
    imageView.context.apply {
        when (searchType){
            SearchHistory.SearchType.HISTORY->{
                imageView.setImageResource(R.drawable.ic_history)
            }
            SearchHistory.SearchType.SUGGESTION->{
                imageView.setImageResource(R.drawable.ic_search)
            }
        }
    }
}