package com.pnam.watchingsocceronline.presentationphone.ui.base

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback

abstract class BaseViewHolder<BD : ViewDataBinding, D : Parcelable> : RecyclerView.ViewHolder {

    private var _binding: BD? = null
    protected val binding: BD get() = _binding!!
    private val containerItemCallback: ContainerItemCallback<D>

    private constructor (binding: BD, containerItemCallback: ContainerItemCallback<D>) : super(
        binding.root
    ) {
        this._binding = binding
        this.containerItemCallback = containerItemCallback
    }

    constructor(
        parent: ViewGroup,
        @LayoutRes layout: Int,
        containerItemCallback: ContainerItemCallback<D>
    ) : this(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), layout, parent, false
        ), containerItemCallback
    )

    abstract fun onBind(data: D)
}