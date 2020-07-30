package com.example.recyclerviewmission2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdater:RecyclerView.Adapter<CustomAdater.ViewHolder>(),OnClickItemListener {
    var items=ArrayList<Custom>()
    var clickItemListener:OnClickItemListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        var rootView=inflater.inflate(R.layout.custom,parent,false)
        return ViewHolder(rootView,this)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var item=items[position]
        holder.setItem(item)
    }
    fun addItem( item: Custom) {
        items.add(item)
    }
    fun getItem(p0:Int) :Custom{
        return items[p0]
    }
    fun setOnClickListener(listener:OnClickItemListener){
        this.clickItemListener=listener
    }
    override fun onClickMethod(ViewHolder: CustomAdater.ViewHolder, View: View,p0:Int){
        clickItemListener?.onClickMethod(ViewHolder,View,p0)
    }
    class ViewHolder(itemView: View,Listener:OnClickItemListener): RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        var textView2: TextView
        var imageView: ImageView
        fun setItem(item:Custom){
            textView.setText(item.name)
            textView2.setText(item.phone)
            imageView.setImageResource(item.image)
        }
        init{
            textView=itemView.findViewById(R.id.textView)
             textView2=itemView.findViewById(R.id.textView2)
             imageView=itemView.findViewById(R.id.imageView)
        }

    }
}