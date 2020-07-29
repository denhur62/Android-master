package com.example.serviceexample

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                var Text=editText.text.toString()
                intent= Intent(applicationContext,MyService::class.java)
                intent.putExtra("tag",Text)
                startService(intent)
            }
        })
//        var receiver = MyReceiver()
//        var filter = IntentFilter()
//        filter.addAction("com.example.broadcast.SHOW")
//        registerReceiver(receiver,filter)
//
//        var passedIntent=Intent()
//        processIntent(passedIntent)

    }

    override fun onNewIntent(intent: Intent) {
        processIntent(intent)
        super.onNewIntent(intent)
    }
    fun processIntent(intent:Intent){
        if(intent!=null){
            var tag=intent.getStringExtra("tag")
            textView.text=tag
        }
    }
}