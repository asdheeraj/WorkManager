package com.dheeraj.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workTag = "notificationWork"

        val dbEventIDTag = "DBEvent"
        val dbEventID = 2
        val inputData = Data.Builder().putInt(dbEventIDTag, dbEventID).build()

        val notificationWork = PeriodicWorkRequest.Builder(NotifyWorker::class.java,5,TimeUnit.SECONDS)
            .setInputData(inputData)
            .addTag(workTag)
            .build()

        WorkManager.getInstance().enqueue(notificationWork)
    }
}
