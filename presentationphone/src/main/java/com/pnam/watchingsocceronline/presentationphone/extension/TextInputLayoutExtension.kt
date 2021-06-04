package com.pnam.watchingsocceronline.presentationphone.extension

import com.google.android.material.textfield.TextInputLayout

val TextInputLayout.text: String
    get() {
        editText ?: return ""
        return editText!!.text.toString()
    }