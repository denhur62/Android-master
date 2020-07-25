package com.example.example4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        button2.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent= Intent(applicationContext,MainActivity::class.java)
                intent.putExtra("201","고객관리")
                setResult(201,intent)
                finish()
            }
        })
        button3.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent= Intent(applicationContext,MainActivity::class.java)
                intent.putExtra("201","매출관리")
                setResult(201,intent)
                finish()
            }
        })
        button4.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent= Intent(applicationContext,MainActivity::class.java)
                intent.putExtra("201","상품관리")
                setResult(201,intent)
               finish()
            }
        })
    }
}