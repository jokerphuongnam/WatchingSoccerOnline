package com.pnam.watchingsocceronline.data.utils

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

internal fun ByteArray.toMultipartBody(partName: String): MultipartBody.Part {
    return MultipartBody.Part.createFormData(
        partName,
        "filename.jpeg",
        RequestBody.create(
            MediaType.parse("image/jpeg"),
            this
        )
    )
}