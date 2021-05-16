package com.pnam.watchingsocceronline.presentationphone.extensionview

import android.widget.ImageView
import coil.request.ImageRequest

class ImageViewExtensionProperty {
    private var _imageBuilder: ImageRequest.Builder.() -> Unit = {}
    var imageBuilder: ImageRequest.Builder.() -> Unit
        get() = _imageBuilder
        set(value) {
            _imageBuilder = value
        }
}

private val ImageView.imageViewExtensionProperty: ImageViewExtensionProperty by lazy {
    ImageViewExtensionProperty()
}

var ImageView.imageBuilder: ImageRequest.Builder.() -> Unit
    get() = imageViewExtensionProperty.imageBuilder
    set(value) {
        imageViewExtensionProperty.imageBuilder = value
    }