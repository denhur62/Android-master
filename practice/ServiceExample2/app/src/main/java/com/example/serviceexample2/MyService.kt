package com.example.serviceexample2

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
        var showIntent=Intent()
        showIntent.setAction("com.example.broadcast.SHOW");
        showIntent.putExtra("tag",tag)
        sendBroadcast(showIntent)


    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
