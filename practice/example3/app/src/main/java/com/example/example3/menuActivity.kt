package com.example.example3


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_menu.*

class menuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        processIntent()

    }

    private fun processIntent() {

            val data= intent.getParcelableExtra<SimpleData>(KEY_SIMPLE_DATA)
            if (data != null) {
                textView.text = "전달 받은 데이터\nNumber : " + data.number
                    .toString() + "\nMessage : " + data.message
            } else {
                textView.text = "전달받지 못함"
            }
        }
    companion object {
        const val KEY_SIMPLE_DATA="data"
    }
}
//    if (intent != null)
//    {
//        val bundle = intent.extras
//        val data: SimpleData =
//            bundle!!.getParcelable(org.techtown.sampleparcelable.MenuActivity.KEY_SIMPLE_DATA)
//        if (data != null) {
//            textView.text = "전달 받은 데이터\nNumber : " + data.number
//                .toString() + "\nMessage : " + data.message
//        }
//    }


