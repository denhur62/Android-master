package com.example.viewpager

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent==null)
            return Service.START_STICKY
        else
            processCommand(intent)
        return super.onStartCommand(intent, flags, startId)
    }
    fun processCommand(intent:Intent){
        var command:String? = intent.getStringExtra("command")
        var name:String?= intent.getStringExtra("name")
        Log.d("태그",name!!)
        var showIntent =Intent(applicationContext,MainActivity::class.java)

        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        showIntent.putExtra("command","show")
        showIntent.putExtra("name",name+"from service")
        startActivity(showIntent)

    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }
}
