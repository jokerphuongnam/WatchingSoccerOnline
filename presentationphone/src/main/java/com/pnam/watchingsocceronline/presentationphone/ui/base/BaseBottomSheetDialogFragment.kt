package com.pnam.watchingsocceronline.presentationphone.ui.base

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<BD : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes override val layoutRes: Int
) : BottomSheetDialogFragment(), BaseScreen<BD, VM> {
    override var _actionBar: ActionBar? = null
    override val actionBar: ActionBar by lazy { (requireActivity() as AppCompatActivity).supportActionBar!! }
    override var _binding: BD? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<BD>(layoutInflater, layoutRes, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCreateView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    protected val actionBarSize: Int
        get() {
            return TypedValue.complexToDimensionPixelSize(
                TypedValue().data,
                resources.displayMetrics
            )
        }
}