package com.example.example2

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val REQUSTCODE=101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //intent로 activity를 부르는 2가지 방법
       button.setOnClickListener(object: View.OnClickListener{
           override fun onClick(p0: View?) {
              // LayoutInflater.from(applicationContext).inflate(R.layout.sublayout, container, true)
               val intent= Intent(applicationContext,MainActivity2::class.java)
               startActivityForResult(intent,REQUSTCODE)
           }
       })
        button2.setOnClickListener( object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent()
                val name = ComponentName("com.example.example2", "com.example.example2.MainActivity2")
                intent.component = name
                startActivityForResult(intent, 101)
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUSTCODE){
            Toast.makeText(this,"요청 코드: "+REQUSTCODE,Toast.LENGTH_LONG).show()
            if(resultCode== RESULT_OK){
                Toast.makeText(this,"결과 코드: "+REQUSTCODE,Toast.LENGTH_LONG).show()
            }
        }

    }

}

