package com.pnam.watchingsocceronline.presentationphone.ui.main.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior


class CustomBottomSheet<V : View>(private val context: Context, attrs: AttributeSet) :
    BottomSheetBehavior<V>(context, attrs) {

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: V,
        event: MotionEvent
    ): Boolean {
        val heightDp: Float = event.rawY / context.resources.displayMetrics.density
        if (state != STATE_EXPANDED) {
            return super.onInterceptTouchEvent(parent, child, event)
        }
        if (state == STATE_EXPANDED && heightDp in 0.0..250.0) {
            return super.onInterceptTouchEvent(parent, child, event)
        }
        return false
    }

    companion object {
        internal fun <V : View> from(view: V): CustomBottomSheet<V> =
            BottomSheetBehavior.from(view) as CustomBottomSheet<V>
    }
}