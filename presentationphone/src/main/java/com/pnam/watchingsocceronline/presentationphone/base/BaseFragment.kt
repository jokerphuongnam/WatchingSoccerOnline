package com.pnam.watchingsocceronline.presentationphone.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<BD : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes override val layoutRes: Int
) : Fragment(), BaseScreen<BD, VM> {
    override var _actionBar: ActionBar? = null
    override val actionBar: ActionBar by lazy { (requireActivity() as AppCompatActivity).supportActionBar!! }
    override var _binding: BD? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate<BD>(layoutInflater, layoutRes, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override val actionBarSize: Int by lazy {
//        TypedValue.complexToDimensionPixelSize(TypedValue().data, requireActivity().resources.displayMetrics)
//    }
}