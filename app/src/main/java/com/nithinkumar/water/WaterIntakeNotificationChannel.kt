package com.nithinkumar.water

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nithinkumar.water.Activity.WaterActivity

class WaterIntakeNotificationChannel {

    fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // 2
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            // 3
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(context: Context) {
        val channelId = "${context.packageName}-${context.getString(R.string.app_name)}"
// 2
        val notificationBuilder = NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.drawable.ic_dashboard_black_24dp) // 3
            setContentTitle("Water Intake Reminder") // 4
            setContentText("Time for another Sip, Please add your consumption") // 5
            setStyle(NotificationCompat.BigTextStyle().bigText("The water intake has to be recorded for us to help you stay hydrated")) // 6
            priority = NotificationCompat.PRIORITY_DEFAULT // 7
            setAutoCancel(false) // 8
            val intent = Intent(context, WaterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // 2
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0) // 3
            setContentIntent(pendingIntent)

        }
        val notificationManager = NotificationManagerCompat.from(context) // 2
        notificationManager.notify(1001, notificationBuilder.build())
    }
}