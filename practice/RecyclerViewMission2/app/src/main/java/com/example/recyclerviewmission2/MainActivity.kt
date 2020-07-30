package com.example.recyclerviewmission2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var layOutManager=GridLayoutManager(this,1)
        var recyclerView=findViewById<RecyclerView>(R.id.RecyclerView)
        recyclerView.layoutManager=layOutManager
        var adater=CustomAdater()
        adater.addItem(Custom("허준현","1997-06-28",R.drawable.ic_launcher_foreground))
        recyclerView.adapter=adater
        adater.setOnClickListener(object: OnClickItemListener{
            override fun onClickMethod(ViewHolder: CustomAdater.ViewHolder, View: View, p0: Int) {
                val item = adater.getItem(p0)
                Toast.makeText(applicationContext, "아이템 선택됨 : " + item?.name, Toast.LENGTH_LONG).show()
            }
        })

    }
}