package com.pnam.watchingsocceronline.presentationphone.utils

import android.os.Parcelable

interface ContainerItemCallback<D: Parcelable> {
    fun onLongTouch(data: D)
    fun onClick(data: D)
}