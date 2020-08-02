package com.example.looperthread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var handler = Handler()
    var thread: ProcessThread? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val input = editText.getText().toString()
            val message = Message.obtain()
            message.obj = input
            thread!!.processHandler.sendMessage(message)
        }
        thread = ProcessThread()
    }
    inner class ProcessThread :Thread(){
        var processHandler=ProcessHandler()
        override fun run() {
            Looper.prepare()
            Looper.loop()
        }
        inner class ProcessHandler:Handler(){
            override fun handleMessage(msg: Message) {
                var str=msg.obj.toString()+"From Thread"
                handler.post{ textView!!.text=str}
            }
        }
    }
//    inner class ProcessThread : Thread() {
//        var processHandler = ProcessHandler()
//        override fun run() {
//            Looper.prepare()
//            Looper.loop()
//        }
//
//         inner class ProcessHandler : Handler() {
//            override fun handleMessage(msg: Message) {
//                val output = msg.obj.toString() + " from thread."
//                handler.post { textView!!.text = output }
//            }
//        }
//    }
}
