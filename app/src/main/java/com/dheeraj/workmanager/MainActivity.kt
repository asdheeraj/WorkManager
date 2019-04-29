package com.dheeraj.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = OneTimeWorkRequestBuilder<NotifyWorker>()
            .setInitialDelay(10,TimeUnit.SECONDS)
            .build()

        val periodicRequest = PeriodicWorkRequestBuilder<NotifyWorker>(10,TimeUnit.SECONDS)
            .build()

        val worker = WorkManager.getInstance()
        worker.beginWith(request).enqueue()
    }
}
