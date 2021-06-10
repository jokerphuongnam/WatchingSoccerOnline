package com.pnam.watchingsocceronline.presentationphone.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.R

@BindingAdapter(value = ["user_url_image"], requireAll = false)
fun setUserImageFromUrl(
    imageView: ImageView,
    url: String?
) {
    url ?: return
    val builder: ImageRequest.Builder.() -> Unit = {
        transformations(CircleCropTransformation())
        crossfade(true)
        placeholder(R.drawable.ic_error)
    }

    if (url.equals("N/A", ignoreCase = true)) {
        imageView.load(R.drawable.ic_user, builder = builder)
    } else {
        imageView.load(url, builder = builder)
    }
}

@BindingAdapter(value = ["url_image", "image_builder"], requireAll = false)
fun setImageFromUrl(
    imageView: ImageView,
    url: String?,
    builder: (ImageRequest.Builder.() -> Unit)? = null
) {
    url ?: return
    if (url.equals("N/A", ignoreCase = true)) {
        if (builder == null) {
            imageView.load(R.drawable.ic_empty)
        } else {
            imageView.load(R.drawable.ic_empty, builder = builder)
        }
    } else {
        if (builder == null) {
            imageView.load(url)
        } else {
            imageView.load(url, builder = builder)
        }
    }
}

@BindingAdapter("search_type")
fun historyOrSearch(imageView: ImageView, searchType: SearchHistory.SearchType) {
    imageView.context.apply {
        when (searchType) {
            SearchHistory.SearchType.HISTORY -> {
                imageView.setImageResource(R.drawable.ic_history)
            }
            SearchHistory.SearchType.SUGGESTION -> {
                imageView.setImageResource(R.drawable.ic_search)
            }
        }
    }
}

@BindingAdapter("is_like")
fun isLike(imageView: ImageView, reactVideo: Video.ReactVideo?) {
    reactVideo?.let {
        if (reactVideo == Video.ReactVideo.LIKE) {
            imageView.setImageResource(R.drawable.ic_like_full)
        } else {
            imageView.setImageResource(R.drawable.ic_like)
        }
    }
}

@BindingAdapter("is_dislike")
fun isDislike(imageView: ImageView, reactVideo: Video.ReactVideo?) {
    reactVideo?.let {
        if (reactVideo == Video.ReactVideo.DISLIKE) {
            imageView.setImageResource(R.drawable.ic_dislike_full)
        } else {
            imageView.setImageResource(R.drawable.ic_dislike)
        }
    }
}