package com.example.requestvalley

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { makeRequest() }
        if (requestQueue == null) {
            requestQueue=Volley.newRequestQueue(applicationContext)
        }
    }
    fun makeRequest(){
        var url=editText!!.text.toString()
        val request: StringRequest = object: StringRequest(Method.GET,url
        , Response.Listener { response -> println("응답 -> $response")  }
        , Response.ErrorListener { error-> println("에러 ->"+ error.message) }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): MutableMap<String, String> {
                return super.getParams()
            }
        }
        request.setShouldCache(false)
        requestQueue!!.add(request)
        println("요청 보냄.")
    }

    fun println(data: String) {
        textView!!.append("""$data""".trimIndent())
    }

    companion object {
        var requestQueue: RequestQueue? = null
    }
}
