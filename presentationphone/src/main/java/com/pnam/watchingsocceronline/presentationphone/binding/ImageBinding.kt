package com.pnam.watchingsocceronline.presentationphone.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.ImageRequest
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.presentationphone.R

@BindingAdapter(value = ["url_image", "image_builder"], requireAll = false)
fun setImageFromUrl(
    imageView: ImageView,
    url: String?,
    builder: (ImageRequest.Builder.() -> Unit)? = null
) {
    if (url == null) {
        imageView.setImageResource(R.drawable.ic_user)
    } else {
        if (builder == null) {
            imageView.load(url)
        } else {
            imageView.load(url, builder = builder)
        }
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