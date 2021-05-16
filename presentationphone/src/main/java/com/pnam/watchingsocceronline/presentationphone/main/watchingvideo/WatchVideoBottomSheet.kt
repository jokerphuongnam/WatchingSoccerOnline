package com.pnam.watchingsocceronline.presentationphone.main.watchingvideo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pnam.watchingsocceronline.presentationphone.databinding.BottomSheetWatchVideoBinding

class WatchVideoBottomSheet(private val transaction: FragmentTransaction) :
    BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetWatchVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetWatchVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var _behavior: BottomSheetBehavior<*>

    fun <VG : ViewGroup> show(behavior: BottomSheetBehavior<VG>) {
        show(transaction, javaClass.simpleName)
        isCancelable = false
        _behavior = behavior
    }
}