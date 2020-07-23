package com.example.example3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val REQUEST_CODE_MENU = 101
    val KEY_SIMPLE_DATA = "data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            val intent = Intent(applicationContext, menuActivity::class.java)
            val data = SimpleData(100, "Hello Android!")
            intent.putExtra(KEY_SIMPLE_DATA, data)
            startActivity(intent)
        }
    }
//    protected fun restoreState() {
//        val pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
//        if (pref != null && pref.contains("name")) {
//            val name = pref.getString("name", "")
//            nameInput.setText(name)
//        }
//    }
//
//    protected fun saveState() {
//        val pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
//        val editor = pref.edit()
//        editor.putString("name", nameInput.getText().toString())
//        editor.commit()
//    }
//
//    protected fun clearState() {
//        val pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
//        val editor = pref.edit()
//        editor.clear()
//        editor.commit()
//    }
}