package com.example.simpleproject

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.UnsupportedEncodingException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sendButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val message = inputMessage.getText().toString()
                Toast.makeText(applicationContext, "전송할 메시지\n\n$message", Toast.LENGTH_LONG).show()
            }

        })
        val Watcher : TextWatcher = object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        }
        closeButton.setOnClickListener { finish() }
        val watcher: TextWatcher = object : TextWatcher {
            override fun onTextChanged(str: CharSequence, start: Int, before: Int, count: Int
            ) {
                var bytes: ByteArray? = null
                try {
                    bytes = str.toString().toByteArray(charset("KSC5601"))
                    val strCount = bytes.size
                    inputCount.setText("$strCount / 80바이트")
                } catch (ex: UnsupportedEncodingException) {
                    ex.printStackTrace()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(strEditable: Editable) {
                val str = strEditable.toString()
                try {
                    val strBytes = str.toByteArray(charset("KSC5601"))
                    if (strBytes.size > 80) {
                        strEditable.delete(strEditable.length - 2, strEditable.length - 1)
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
        inputMessage.addTextChangedListener(watcher)
    }
}
