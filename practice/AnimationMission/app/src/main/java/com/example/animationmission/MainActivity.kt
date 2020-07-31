package com.example.animationmission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var iscontainer=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var AnimationListener= SlideListener()
        var left=AnimationUtils.loadAnimation(this,R.anim.left)
        var right=AnimationUtils.loadAnimation(this,R.anim.right)
        var container=findViewById<LinearLayout>(R.id.container)
        left.setAnimationListener(AnimationListener)
        right.setAnimationListener(AnimationListener)
        button.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                if(iscontainer==false){
                    container.setVisibility(View.VISIBLE)
                    container.startAnimation(left)
                    iscontainer=true
                }

            }
        })
        button2.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                if(iscontainer){
                    container.setVisibility(View.INVISIBLE)
                    container.startAnimation(right)
                    iscontainer=false
                }
            }
        })
    }

    class SlideListener:Animation.AnimationListener{
        override fun onAnimationRepeat(p0: Animation?) {

        }

        override fun onAnimationEnd(p0: Animation?) {

        }

        override fun onAnimationStart(p0: Animation?) {

        }
    }
}