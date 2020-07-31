package com.example.tweenanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
               var ani=AnimationUtils.loadAnimation(applicationContext,R.anim.scale)
                p0?.startAnimation(ani)
            }
        })
    }
}