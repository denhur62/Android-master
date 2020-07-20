package org.techtown.sampledialog

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var textView: TextView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener(object : OnClickListener() {
            fun onClick(view: View?) {
                showMessage()
            }
        })
    }

    private fun showMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("안내")
        builder.setMessage("종료하시겠습니까?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("예", object : OnClickListener() {
            fun onClick(dialog: DialogInterface?, whichButton: Int) {
                val message = "예 버튼이 눌렸습니다. "
                textView.setText(message)
            }
        })
        builder.setNeutralButton("취소", object : OnClickListener() {
            fun onClick(dialog: DialogInterface?, whichButton: Int) {
                val message = "취소 버튼이 눌렸습니다. "
                textView.setText(message)
            }
        })
        builder.setNegativeButton("아니오", object : OnClickListener() {
            fun onClick(dialog: DialogInterface?, whichButton: Int) {
                val message = "아니오 버튼이 눌렸습니다. "
                textView.setText(message)
            }
        })
        val dialog = builder.create()
        dialog.show()
    }
}