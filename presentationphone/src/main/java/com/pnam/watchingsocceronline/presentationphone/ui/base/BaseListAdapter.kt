package com.pnam.watchingsocceronline.presentationphone.ui.base

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback

abstract class BaseListAdapter<D : Parcelable, VH : BaseListAdapter.BaseViewHolder<*, D>>(diff: DiffUtil.ItemCallback<D>) :
    ListAdapter<D, VH>(diff) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

    abstract class BaseViewHolder<BD : ViewDataBinding, D : Parcelable> : RecyclerView.ViewHolder {

        private var _binding: BD? = null
        protected val binding: BD get() = _binding!!
        protected val itemCallback: ContainerItemCallback<D>?

        private constructor (binding: BD, containerItemCallback: ContainerItemCallback<D>?) : super(
            binding.root
        ) {
            this._binding = binding
            this.itemCallback = containerItemCallback
        }

        constructor(
            parent: ViewGroup,
            @LayoutRes layout: Int,
            containerItemCallback: ContainerItemCallback<D>? = null
        ) : this(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), layout, parent, false
            ), containerItemCallback
        )

        abstract fun onBind(data: D)
    }
}