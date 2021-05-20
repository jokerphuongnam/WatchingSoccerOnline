package com.pnam.watchingsocceronline.presentationphone.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.pnam.watchingsocceronline.model.model.SearchHistory
import com.pnam.watchingsocceronline.presentationphone.R

@BindingAdapter(value = ["url_image"], requireAll = false)
fun setImageFromUrl(imageView: ImageView, url: String?) {
    url?.let {
        imageView.load(it)
    }
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