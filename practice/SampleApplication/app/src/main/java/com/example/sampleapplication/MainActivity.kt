package com.example.sampleapplication

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var seekbar : SeekBar? = findViewById(R.id.seekBar1)
        var progressbar : ProgressBar? = findViewById(R.id.progressBar1)
       seekbar?.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
           override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    progressbar?.setProgress(p1)
                    Text.setText(p1.toString())

           }

           override fun onStartTrackingTouch(p0: SeekBar?) {

           }

           override fun onStopTrackingTouch(p0: SeekBar?) {

           }

       })
    }
}