package org.techtown.sampletoast

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    var editText: EditText? = null
    var editText2: EditText? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        editText2 = findViewById(R.id.editText2)
    }

    fun onButton1Clicked(v: View?) {
        try {
            val toastView: Toast = Toast.makeText(this, "위치가 바뀐 토스트 메시지입니다.", Toast.LENGTH_LONG)
            val xOffset: Int = editText.getText().toString().toInt()
            val yOffset: Int = editText2.getText().toString().toInt()
            toastView.setGravity(Gravity.TOP or Gravity.TOP, xOffset, yOffset)
            toastView.show()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }

    fun onButton2Clicked(v: View?) {
        val inflater: LayoutInflater = getLayoutInflater()
        val layout: View = inflater.inflate(
                R.layout.toastborder,
                findViewById<View>(R.id.toast_layout_root) as ViewGroup)
        val text: TextView = layout.findViewById(R.id.text)
        val toast = Toast(this)
        text.setText("모양 바꾼 토스트")
        toast.setGravity(Gravity.CENTER, 0, -100)
        toast.setDuration(Toast.LENGTH_SHORT)
        toast.setView(layout)
        toast.show()
    }

    fun onButton3Clicked(v: View?) {
        Snackbar.make(v, "스낵바입니다.", Snackbar.LENGTH_LONG).show()
    }
}