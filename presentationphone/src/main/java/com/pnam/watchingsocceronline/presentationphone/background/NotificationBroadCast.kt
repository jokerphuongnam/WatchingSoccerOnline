package com.pnam.watchingsocceronline.presentationphone.background

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.presentationphone.R

class NotificationBroadCast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        createNotificationChannel(context)
        intent.getParcelableExtra<Notification>(NOTIFICATION)?.let { notification ->
            val notificationBuilder = NotificationCompat.Builder(context, NOTIFICATION).apply {
                setDefaults(NotificationCompat.DEFAULT_ALL)
                setAutoCancel(false)
                setSmallIcon(R.drawable.ic_download)
                priority = NotificationCompat.PRIORITY_MAX
                setContentTitle(notification.title)
            }
            with(NotificationManagerCompat.from(context)) {
                notify(NOTIFICATION_ID, notificationBuilder.build())
            }
        }
    }

    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = NOTIFICATION
            // Register the channel with the system
            val notificationManager: NotificationManager? =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
            notificationManager?.getNotificationChannel(name).let {
                if (it == null) {
                    notificationManager?.createNotificationChannel(
                        NotificationChannel(
                            NOTIFICATION,
                            name,
                            NotificationManager.IMPORTANCE_LOW
                        ).apply {
                            description = "This channel for notification video"
                        })
                }
            }
        }
    }

    companion object {
        const val NOTIFICATION: String = "notification"
        const val NOTIFICATION_ID: Int = 32424
    }
}