package com.example.customview2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.layout1.view.*

class Layout1 : LinearLayout {
    constructor(context: Context?) : super(context){
        init(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context)
    }
    fun init(context:Context?){
        var inflater=context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout1,this,true)

    }
    fun setImage(resId:Int){

        imageView.setImageResource(resId)
    }
    fun setName(name:String){
        textView.setText(name)
    }
    fun setMobile(mobile:String){
        textView2.setText(mobile)
    }
}