package com.pnam.watchingsocceronline.presentationphone.ui.base

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pnam.watchingsocceronline.presentationphone.R

abstract class BaseBottomSheetDialogFragment<BD : ViewDataBinding, VM : BaseViewModel, AVM : BaseViewModel>(
    @LayoutRes override val layoutRes: Int
) : BottomSheetDialogFragment(), BaseScreen<BD, VM> {
    protected abstract val activityViewModel: AVM
    override var _actionBar: ActionBar? = null
    override val actionBar: ActionBar by lazy { (requireActivity() as AppCompatActivity).supportActionBar!! }
    override var _binding: BD? = null
    protected lateinit var dialog: BottomSheetDialog

    protected lateinit var behavior: BottomSheetBehavior<*>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                dialog = it as BottomSheetDialog
                behavior =
                    BottomSheetBehavior.from(dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!)
                onCreateView()
            }
        }
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun setupFullHeight() {
        val bottomSheet = dialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
        val layoutParams: ViewGroup.LayoutParams = bottomSheet.layoutParams
        val windowHeight: Int = getWindowHeight()
        layoutParams.height = windowHeight
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    protected val actionBarSize: Int
        get() {
            return TypedValue.complexToDimensionPixelSize(
                TypedValue().data,
                resources.displayMetrics
            )
        }
}