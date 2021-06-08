package com.pnam.watchingsocceronline.presentationphone.extension

import com.google.android.material.textfield.TextInputLayout

var TextInputLayout.text: String
    get() {
        editText ?: return ""
        return editText!!.text.toString()
    }
    set(value) {
        editText?.setText(value)
    }