package com.example.pagesliding

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var isPageOpen=false
    var page:LinearLayout?=null
    var button: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var page = findViewById<LinearLayout>(R.id.page)
        var translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.left)
        var translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.right)
        var pageSlide=SlidingPage()
        translateLeftAnim.setAnimationListener(pageSlide)
        translateRightAnim.setAnimationListener(pageSlide)

        button=findViewById<Button>(R.id.button)
        button!!.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                if (isPageOpen) {
                    page.setVisibility(View.INVISIBLE)
                   page.startAnimation(translateRightAnim)
                } else {
                    page?.setVisibility(View.VISIBLE)
                    page?.startAnimation(translateLeftAnim)
                }
            }
        })
    }
    inner class SlidingPage : Animation.AnimationListener{
        override fun onAnimationRepeat(p0: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
            if (isPageOpen) {

                button?.setText("Open")
                isPageOpen = false
            } else {
                button?.setText("Close")
                isPageOpen = true
            }
        }
        override fun onAnimationStart(p0: Animation?) {
        }
    }
}