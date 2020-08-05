package com.example.threadpost

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var textView: TextView? = null
    var handler : Handler?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val thread = BackgroundThread()
            thread.start()
        }
        handler=Handler()
    }

     inner class BackgroundThread : Thread() {
        var value = 0
        override fun run() {
            for (i in 0..99) {
                try {
                    sleep(1000)
                } catch (e: Exception) {
                }
                value += 1
                Log.d("Thread", "value : $value")
                handler!!.post { textView!!.text = "value 값 : $value" }

            }
        }
    }
}