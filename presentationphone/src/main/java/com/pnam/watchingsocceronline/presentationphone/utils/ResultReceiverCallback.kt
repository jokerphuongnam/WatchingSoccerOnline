package com.pnam.watchingsocceronline.presentationphone.utils


interface ResultReceiverCallback<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception)
}