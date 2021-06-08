package com.pnam.watchingsocceronline.presentationphone.utils

import androidx.annotation.StringRes
import com.pnam.watchingsocceronline.presentationphone.R

@StringRes
@Throws(NoErrorException::class)
fun String.emailRegex(): Int = when {
    indexOf('@') == -1 -> {
        R.string.email_need_at
    }

    isEmpty() -> {
        R.string.email_empty
    }
    length < 6 -> {
        R.string.email_length
    }
    indexOf(' ') != -1 -> {
        R.string.email_cannot_be_spaces
    }
    else -> {
        throw NoErrorException()
    }
}

@StringRes
@Throws(NoErrorException::class)
fun String.passwordRegex(): Int = when {
    isEmpty() -> {
        R.string.password_empty
    }
    length < 6 -> {
        R.string.password_length
    }
    indexOf(' ') != -1 -> {
        R.string.password_cannot_be_spaces
    }
    else -> {
        throw NoErrorException()
    }
}

@Throws(NoErrorException::class)
@StringRes
fun String.nameRegex(): Int = when {
    isEmpty() -> {
        R.string.name_empty
    }
    length < 1 -> {
        R.string.name_length
    }
    else -> {
        throw NoErrorException()
    }
}
