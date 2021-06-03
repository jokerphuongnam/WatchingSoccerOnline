package com.pnam.watchingsocceronline.presentationphone.ui.base

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.res.Resources
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kotlin.jvm.Throws

abstract class BaseFragment<BD : ViewDataBinding, VM : BaseViewModel, AVM : BaseViewModel>(
    @LayoutRes override val layoutRes: Int
) : Fragment(), BaseScreen<BD, VM> {
    protected abstract val activityViewModel: AVM
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
        onCreateView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Throws(Resources.NotFoundException::class)
    protected inline fun <reified S : Service> getService(): S {
        val activityManager: ActivityManager =
            requireContext().getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        @Suppress("DEPRECATION") val runningServices: List<ActivityManager.RunningServiceInfo> =
            activityManager.getRunningServices(Integer.MAX_VALUE)
        for (serviceRunning in runningServices) {
            if (serviceRunning is S) {
                return serviceRunning
            }
        }
        throw Resources.NotFoundException()
    }

    protected fun removeThisFragmentFromBackStack(){
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.commit {
            remove(this@BaseFragment)
        }
        fragmentManager.popBackStack()
    }

    protected val actionBarSize: Int
        get() {
            return TypedValue.complexToDimensionPixelSize(
                TypedValue().data,
                resources.displayMetrics
            )
        }
}