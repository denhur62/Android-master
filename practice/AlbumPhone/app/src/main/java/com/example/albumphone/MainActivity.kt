package com.example.albumphone

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener { openGallery() }

    }

    fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                val fileUri = data!!.data
                val resolver = contentResolver
                try {
                    val instream = resolver.openInputStream(fileUri!!)
                    val imgBitmap = BitmapFactory.decodeStream(instream)
                    imageView!!.setImageBitmap(imgBitmap)
                    instream!!.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


}
