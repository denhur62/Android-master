package com.example.customview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layout1.setImage(R.drawable.image3);
        layout1.setName("김민수");
        layout1.setMobile("010-1000-1000");
        button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                layout1.setImage(R.drawable.image3)
            }
        })
        button2.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                layout1.setImage(R.drawable.image5)
            }
        })
    }
}