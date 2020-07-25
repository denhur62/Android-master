package com.example.example4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // var mainFragment = supportFragmentManager.findFragmentById(R.id.mainfragment) as MainFragment
    }
    fun onFragmentChanged(index: Int) {

        if (index == 0) {
            supportFragmentManager.beginTransaction().replace(R.id.container, MenuFragment()).addToBackStack(null).commit()
        } else if (index == 1) {
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).addToBackStack(null).commit()
        }
    }
}