package com.pnam.watchingsocceronline.presentationphone.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.pnam.watchingsocceronline.presentationphone.R

abstract class BaseActivity<BD : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes override val layoutRes: Int
) : AppCompatActivity(), BaseScreen<BD, VM> {

    override var _actionBar: ActionBar? = null
    override val actionBar: ActionBar by lazy { supportActionBar!! }


    override var _binding: BD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView<BD>(this, layoutRes).apply {
            lifecycleOwner = this@BaseActivity
        }
        createView()
    }

    protected val isEmptyFragmentBackStack: Boolean
        get() = supportFragmentManager.backStackEntryCount == 0

    private var clickFirstTime: Long = 0

    protected fun twiceTimeToExit() {
        if (clickFirstTime == 0L) {
            clickFirstTime = System.currentTimeMillis()
            showToast(getString(R.string.mess_when_click_back_btn))
        } else {
            if (System.currentTimeMillis() - clickFirstTime < 2000L) {
                finishAffinity()
            }
        }
    }

//    override val actionBarSize: Int by lazy {
//        TypedValue.complexToDimensionPixelSize(TypedValue().data, resources.displayMetrics)
//    }
}