package com.dheeraj.workmanager

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color


class NotifyWorker(private val context: Context, parameters: WorkerParameters) : Worker(context,parameters) {

    override fun doWork(): Result {
        Log.d(TAG,"In do work")
        triggerNotification()
        return Result.success()
    }

    private fun triggerNotification(){

        val notificationId = 234

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "my_channel_01"
        val name = "my_channel"
        val description = "This is my channel"

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            channel.setShowBadge(false)
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context,channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("My notification")
            .setContentText("Hello World!")

        with(notificationManager){
            notify(notificationId,builder.build())
        }
    }

    companion object {
         val TAG = NotifyWorker::class.java.simpleName
    }

}