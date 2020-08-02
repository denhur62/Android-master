package com.example.asynctaskpractice

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.AsyncTaskLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var task: BackgroundTask? = null
    var value = 0
    var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            task = BackgroundTask()
            task!!.execute(10,20)
        }
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener { task!!.cancel(true) }
    }

     inner class BackgroundTask : AsyncTask<Int?, Int?, Int>() {
        override fun onPreExecute() {
            value = 0
            progressBar!!.progress = value
        }

         override fun doInBackground(vararg p0: Int?): Int {
             while (isCancelled == false) {
                 value++
                 if (value >= 100) {
                     break
                 } else {
                     publishProgress(value)
                 }
                 try {
                     Thread.sleep(100)
                 } catch (ex: InterruptedException) {
                 }
             }
             return value
         }

         override fun onProgressUpdate(vararg values: Int?) {
             progressBar!!.progress = values[0]!!
         }

         override fun onCancelled(result: Int?) {
             progressBar!!.progress = 0
         }


        override fun onCancelled() {
            progressBar!!.progress = 0
        }
    }
}
