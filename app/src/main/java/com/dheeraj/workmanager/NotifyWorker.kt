package com.dheeraj.workmanager

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters


class NotifyWorker(context:Context,parameters: WorkerParameters) : Worker(context,parameters) {

    override fun doWork(): Result {
        triggerNotification()
        return Result.success()
    }

    private fun triggerNotification(){

        val mBuilder = NotificationCompat.Builder(applicationContext)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("My notification")
            .setContentText("Hello World!")


        // Gets an instance of the NotificationManager service//

        val mNotificationManager =

            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than create a new one. In this example, the notification’s ID is 001//

     mNotificationManager?.notify(1, mBuilder.build())
    }

}