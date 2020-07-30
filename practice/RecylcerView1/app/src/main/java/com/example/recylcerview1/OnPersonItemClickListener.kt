package com.example.recylcerview1

import android.view.View

interface OnPersonItemClickListener {

    fun onItemClick(holder: personAdapter.ViewHolder?, view: View?, position: Int)
}