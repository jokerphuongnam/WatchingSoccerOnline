package com.pnam.watchingsocceronline.presentationphone.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
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

    protected fun showFragment(
        @IdRes container: Int,
        fragment: Fragment,
        transactionViews: List<View>? = null,
        isHidePreFrag: Boolean = true
    ) {
        val tag = fragment.javaClass.simpleName
        val fragmentFindByTag: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.commit {
            transactionViews?.forEach { transactionView ->
                addSharedElement(transactionView, transactionView.transitionName)
            }
            if (fragmentFindByTag == null) {
                add(container, fragment, tag)
            } else {
                if(isHidePreFrag){
                    supportFragmentManager.findFragmentById(container)?.let {
                        hide(it)
                    }
                }
                show(fragment)
            }
        }
    }
}