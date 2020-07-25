package com.example.example4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class MainFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView= inflater.inflate(R.layout.fragment_main, container, false) as ViewGroup
        val button = rootView.findViewById<Button>(R.id.button)
        button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
               // val activity=activity as MainActivity
                (activity as MainActivity).onFragmentChanged(0)
            }
        })
        return rootView
    }


}