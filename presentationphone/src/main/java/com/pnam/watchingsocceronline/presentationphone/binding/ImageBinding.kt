package com.pnam.watchingsocceronline.presentationphone.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.pnam.watchingsocceronline.presentationphone.extensionview.imageBuilder

@BindingAdapter("url_image")
fun setImageFromUrl(imageView: ImageView, url: String) {
    imageView.load(url, builder = imageView.imageBuilder)
}