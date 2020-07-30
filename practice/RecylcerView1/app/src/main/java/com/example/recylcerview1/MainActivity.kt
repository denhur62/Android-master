package com.example.recylcerview1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var layoutManager=GridLayoutManager(this,2)
        var recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager

        val adapter = personAdapter()
        adapter.addItem(Person("김민수", "010-1000-1000",R.drawable.image4))
        adapter.addItem(Person("김하늘", "010-2000-2000",R.drawable.image6))
        adapter.addItem(Person("홍길동", "010-3000-3000",R.drawable.image7))
        adapter.setItem(2,Person("김민수", "010-1000-1000",R.drawable.image4))

        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : OnPersonItemClickListener {
            override fun onItemClick(holder: personAdapter.ViewHolder?, view: View?, position: Int) {
                val item = adapter.getItem(position)
                Toast.makeText(applicationContext, "아이템 선택됨 : " + item?.name, Toast.LENGTH_LONG).show()
            }
        })
    }
}