package com.example.sampleproject1

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.AlteredCharSequence
import android.text.method.Touch
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.stream.DoubleStream.builder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(p0: View?, p1: MotionEvent): Boolean {
                var action: Int = p1.getAction()
                var curx : Float= p1.getX()
                var cury : Float=p1.getY()
                if(action == MotionEvent.ACTION_DOWN){
                    printText("손가락 누름 :${cury},${curx}")
                }else if(action == MotionEvent.ACTION_MOVE){
                    printText("손가락 움직임:${cury},${curx} ")

                }else if(action == MotionEvent.ACTION_UP){
                    printText("손가락 땜:${cury},${curx} ")
                }
                return true;
            }

        })
        button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                showMessage()
            }

        })
        var dector=GestureDetector(this, object: GestureDetector.OnGestureListener{
            override fun onShowPress(p0: MotionEvent?) {
                printText("onDown() 호출됨.")
            }

            override fun onSingleTapUp(p0: MotionEvent?): Boolean {
                printText("onShowPress() 호출됨.")
                return true
            }

            override fun onDown(p0: MotionEvent?): Boolean {
                printText("onSingleTapUp() 호출됨.")
                return true
            }

            override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
                printText("onScroll() 호출됨 : $p2, $p3")
                return true
            }

            override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
                printText("onFling() 호출됨 : $p2, $p3")
                return true
            }

            override fun onLongPress(p0: MotionEvent?) {
                printText("onLongPress() 호출됨.")
            }

        })
        view2.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                    dector.onTouchEvent(p1)
                return true
            }

        })
    }
    fun printText(str :String){
        textView2.append(str+"\n")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(applicationContext, "시스템 [BACK] 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }


//  토스트 꾸미기
//    fun onButton2Clicked(v: View?) {
//        val inflater: LayoutInflater = getLayoutInflater()
//        val layout: View = inflater.inflate(R.layout.toastborder, findViewById<View>(R.id.activity_main) as ViewGroup)
//        val text: TextView = layout.findViewById(R.id.text)
//        val toast = Toast(this)
//        text.setText("모양 바꾼 토스트")
//        toast.setGravity(Gravity.CENTER, 0, -100)
//        toast.setDuration(Toast.LENGTH_SHORT)
//        toast.setView(layout)
//        toast.show()
//    }
     fun showMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("안내")
        builder.setMessage("종료하시겠습니까?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("예", object : DialogInterface.OnClickListener{
        override fun onClick(p0: DialogInterface?, p1: Int) {
            val message = "예 버튼이 눌렸습니다. "
            textView2.setText(message)
        }

    })

    builder.setNeutralButton("취소", object : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, whichButton: Int) {
            val message = "취소 버튼이 눌렸습니다. "
            textView2.setText(message)
        }
    })
    builder.setNegativeButton("아니오", object : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface?, whichButton: Int) {
            val message = "아니오 버튼이 눌렸습니다. "
            textView2.setText(message)
        }
    })
    val dialog = builder.create().show()

}
}