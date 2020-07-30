package com.example.recyclerviewmission1


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class personAdapter : RecyclerView.Adapter<personAdapter.ViewHolder>(), OnPersonItemClickListener {
    var items = ArrayList<person>()
    var listener: OnPersonItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var inflater=LayoutInflater.from(parent.context)
        var View=inflater.inflate(R.layout.person_item,parent,false)
        return ViewHolder(View,this)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var item=items[position]
        holder.setItem(item)
    }
    fun addItem(item: person) {
        items.add(item)
    }
    fun getItem(position: Int) :person?{
       return items[position]
    }

    fun setOnItemClickListener(listener:OnPersonItemClickListener){
        this.listener = listener
    }

    override fun onItemClick(holder: ViewHolder?, view: View?, position: Int) {
        listener?.onItemClick(holder,view,position)
    }
    class ViewHolder(itemView: View, listener:OnPersonItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        var textView2: TextView
        var textView3: TextView
        var imageView: ImageView
        fun setItem(item: person) {
            textView.setText(item.name)
            textView2.setText(item.birth)
            textView2.setText(item.phone)
            imageView.setImageResource(item.image)
        }

        init {
            textView = itemView.findViewById(R.id.textView)
            textView2 = itemView.findViewById(R.id.textView2)
            textView3 = itemView.findViewById(R.id.textView2)
            imageView = itemView.findViewById(R.id.imageView)
            itemView.setOnClickListener(object: View.OnClickListener{
                override fun onClick(p0: View?) {
                    val position = adapterPosition
                    if(listener!=null)
                        listener.onItemClick(this@ViewHolder,p0,position)
                }
            })
        }

    }
}

