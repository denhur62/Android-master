package com.example.valleymovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var adapter: MovieAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener { makeRequst() }
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(applicationContext)
        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        adapter = MovieAdapter()
        recyclerView.adapter = adapter
    }

    companion object {
        var requestQueue: RequestQueue? = null
    }

    fun makeRequst() {
        var url = editText.text.toString()

        var request: StringRequest = object : StringRequest(Method.GET, url,
            Response.Listener { response ->
                println("응답 -> $response")
                processResponse(response)
            },
            Response.ErrorListener { error -> println("에러 -> " + error.message) }
        ) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                return HashMap()
            }
        }
        request.setShouldCache(false)
        requestQueue!!.add(request)
        println("요청 보냄.")
    }
    fun processResponse( response:String){
        var gson= Gson()
        var movieList=gson.fromJson(response,MovieList::class.java)
        for (i in 0 .. movieList.boxOfficeResult!!.dailyBoxOfficeList.size-1) {
            val movie = movieList.boxOfficeResult!!.dailyBoxOfficeList[i]
            adapter?.addItem(movie)
        }
        adapter?.notifyDataSetChanged()
    }
}