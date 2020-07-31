package com.example.viewpager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                var name: String=editText.text.toString()
                intent = Intent(applicationContext,MyService::class.java)
                intent.putExtra("name",name)
                startService(intent)
            }
        })
        var passedIntent=intent
        processIntent(passedIntent)
    }

    override fun onNewIntent(intent: Intent) {
        processIntent(intent)
        super.onNewIntent(intent)
    }
    fun processIntent(intent:Intent){
        if(intent!=null){
            var command=intent.getStringExtra("command")
            var name=intent.getStringExtra("name")
            Toast.makeText(this,"command: "+command+" ,name: "+name,Toast.LENGTH_LONG).show()
        }
    }
}