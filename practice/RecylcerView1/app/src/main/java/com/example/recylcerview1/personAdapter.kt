package com.example.recylcerview1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class personAdapter : RecyclerView.Adapter<personAdapter.ViewHolder>(), OnPersonItemClickListener {
    var items = ArrayList<Person>()
    var listener: OnPersonItemClickListener? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val itemView = inflater.inflate(R.layout.person_item, viewGroup, false)
        return ViewHolder(itemView,this)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = items[position]
        viewHolder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: Person) {
        items.add(item)
    }

    fun setItem(items: ArrayList<Person>) {
        this.items = items
    }

    fun getItem(position: Int): Person? {
        return items[position]
    }

    fun setItem(position: Int, item: Person) {
        items[position] = item
    }
    fun setOnItemClickListener(listener: OnPersonItemClickListener) {
        this.listener = listener
    }
    override fun onItemClick(holder: ViewHolder?, view: View?, position: Int) {
            listener?.onItemClick(holder, view, position)
    }
    class ViewHolder(itemView: View, listener: OnPersonItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        var textView2: TextView
        var imageView: ImageView
        fun setItem(item: Person) {
            textView.setText(item.name)
            textView2.setText(item.mobile)
            imageView.setImageResource(item.image)
        }

        init {
            textView = itemView.findViewById(R.id.textView)
            textView2 = itemView.findViewById(R.id.textView2)
            imageView= itemView.findViewById(R.id.imageView)
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    val position = adapterPosition

                    if (listener != null) {
                        listener.onItemClick(this@ViewHolder, p0, position)

                    }
                }
            })
        }
    }
}
