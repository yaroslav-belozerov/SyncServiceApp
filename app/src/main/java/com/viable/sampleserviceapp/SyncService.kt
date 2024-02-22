package com.viable.sampleserviceapp

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class SyncService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = buildNotification()
        startForeground(sid, notification)
        return START_STICKY
    }

    private fun buildNotification() : Notification {

        val launchIntent = PendingIntent.getActivity(applicationContext,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_MUTABLE)

        return NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.sync_in_progress))
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setContentIntent(launchIntent)
            .build()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {
        private const val sid = 42
        private const val channelId = "app launch"
    }
}