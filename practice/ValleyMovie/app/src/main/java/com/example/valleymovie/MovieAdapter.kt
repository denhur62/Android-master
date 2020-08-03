package com.example.valleymovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MovieAdapter(): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var items= ArrayList<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var Inflater=LayoutInflater.from(parent.context)
        var rootView=Inflater.inflate(R.layout.movie,parent,false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=items[position]
        holder.setItem(item)
    }
    fun addItem(item : Movie){
        items.add(item)
    }



    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        var textView: TextView?=null
        var textView2: TextView?=null
        init{
            textView=itemview.findViewById(R.id.textView)
            textView2=itemview.findViewById(R.id.textView2)
        }
        fun setItem(item :Movie){
            textView?.setText(item.movieNm)
            textView2?.setText(item.audiCnt+"명")
        }
    }
}