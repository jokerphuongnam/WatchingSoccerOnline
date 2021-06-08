package com.pnam.watchingsocceronline.presentationphone.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.utils.toStringRes


@BindingAdapter("gender")
fun setGender(textView: TextView, gender: User.Gender?) {
    gender?.apply {
        textView.setText(toStringRes())
    }
}