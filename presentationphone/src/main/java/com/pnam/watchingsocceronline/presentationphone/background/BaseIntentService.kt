package com.pnam.watchingsocceronline.presentationphone.background

import android.app.IntentService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

abstract class BaseIntentService(name: String?) : IntentService(name) {

    @Inject
    protected lateinit var serviceScope: CoroutineScope

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        serviceScope.cancel()
    }
}