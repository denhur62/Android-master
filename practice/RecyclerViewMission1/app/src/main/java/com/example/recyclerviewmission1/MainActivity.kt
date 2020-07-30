package com.example.recyclerviewmission1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var adapter = personAdapter()
        adapter.addItem(person("허준현","1997-06-28","01040305852",R.drawable.image4))
        adapter.addItem(person("김형석","1997-03-28","01023234566",R.drawable.image6))

        var layoutManager= GridLayoutManager(this,1)
        var recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object:OnPersonItemClickListener{
            override fun onItemClick(holder: personAdapter.ViewHolder?, view: View?, position: Int) {
                val item = adapter.getItem(position)
                Toast.makeText(applicationContext, "아이템 선택됨 : " + item?.name, Toast.LENGTH_LONG).show()
            }
        })
        button.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                val name = editText.text.toString()
                val birth = editText2.text.toString()
                val phone = editText3.text.toString()
                adapter.addItem(person(name,birth,phone,R.drawable.image4))
                adapter.notifyDataSetChanged()
                textView.text = adapter.itemCount.toString() + " 명"
            }
        })
    }
}