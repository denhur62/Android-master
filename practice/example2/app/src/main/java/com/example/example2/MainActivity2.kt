package com.example.example2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        menuButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
               var intent: Intent? = null
                intent?.putExtra("name","mike")
                setResult(RESULT_OK,intent)
                finish()
            }

        })
    }
}