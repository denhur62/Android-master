package com.example.webviewvelloymisson

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    var handler = Handler()
    var textView: TextView?=null
    var webView: WebView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        webView = findViewById(R.id.webView)
        var settings = webView!!.settings
        settings?.setJavaScriptEnabled(true)
        button.setOnClickListener {
            var urlStr = editText.text.toString().trim()

            var thread = RequestThread(urlStr)
            thread.start()
        }
    }
    inner class RequestThread(var urlStr: String) : Thread() {
        override fun run() {
            try {
                val output = request(urlStr)
                handler.post(Runnable {
                    textView!!.setText(output)
                    Log.d("tag",output)
                    webView!!.loadDataWithBaseURL(null,output, "text/html", "charset=utf-8", "utf-8")
                })
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        private fun request(urlStr: String): String {
            val output = StringBuilder()
            try {
                val url = URL(urlStr)
                var conn = url.openConnection() as HttpURLConnection
                if (conn != null) {
                    conn.connectTimeout = 10000
                    conn.requestMethod = "GET"
                    conn.doInput = true
                    conn.doOutput = true
                    var redirect = false
                    val status = conn.responseCode
                    if (status != HttpURLConnection.HTTP_OK) {
                        if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_SEE_OTHER) redirect = true
                    }

                    if (redirect) {
                        val newUrl = conn.getHeaderField("Location")
                        val cookies = conn.getHeaderField("Set-Cookie")
                        conn = URL(newUrl).openConnection() as HttpURLConnection
                        conn.setRequestProperty("Cookie", cookies)
                        println("Redirect to URL : $newUrl")
                    }
                    val reader = BufferedReader(InputStreamReader(conn.inputStream))
                    var line: String? = null
                    while (true) {
                        line = reader.readLine()
                        if (line == null) {
                            break
                        }
                        output.append("""$line""".trimIndent())
                    }
                    reader.close()
                    conn.disconnect()
                }
            } catch (ex: Exception) {
                Log.e("tag", "Exception in processing response.", ex)
                ex.printStackTrace()
            }
            return output.toString()
        }

    }
}