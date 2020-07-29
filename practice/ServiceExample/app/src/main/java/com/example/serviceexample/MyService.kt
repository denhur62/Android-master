package com.example.serviceexample

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent==null)
            return Service.START_STICKY
        else
            processCommand(intent)
        return super.onStartCommand(intent, flags, startId)
    }
    private fun processCommand(intent:Intent){
        var tag=intent.getStringExtra("tag")
        var showIntent=Intent(applicationContext,MainActivity::class.java)
        showIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        showIntent.putExtra("tag",tag)

       startActivity(showIntent)
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
