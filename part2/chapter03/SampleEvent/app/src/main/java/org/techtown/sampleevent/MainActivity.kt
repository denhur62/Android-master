package org.techtown.sampleevent

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var textView: TextView? = null
    var detector: GestureDetector? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        val view: View = findViewById<View>(R.id.view)
        view.setOnTouchListener(object : OnTouchListener() {
            fun onTouch(view: View?, motionEvent: MotionEvent): Boolean {
                val action: Int = motionEvent.getAction()
                val curX: Float = motionEvent.getX()
                val curY: Float = motionEvent.getY()
                if (action == MotionEvent.ACTION_DOWN) {
                    println("손가락 눌림 : $curX, $curY")
                } else if (action == MotionEvent.ACTION_MOVE) {
                    println("손가락 움직임 : $curX, $curY")
                } else if (action == MotionEvent.ACTION_UP) {
                    println("손가락 뗌 : $curX, $curY")
                }
                return true
            }
        })
        detector = GestureDetector(this, object : OnGestureListener() {
            fun onDown(motionEvent: MotionEvent?): Boolean {
                println("onDown() 호출됨.")
                return true
            }

            fun onShowPress(motionEvent: MotionEvent?) {
                println("onShowPress() 호출됨.")
            }

            fun onSingleTapUp(motionEvent: MotionEvent?): Boolean {
                println("onSingleTapUp() 호출됨.")
                return true
            }

            fun onScroll(motionEvent: MotionEvent?, motionEvent1: MotionEvent?, v: Float, v1: Float): Boolean {
                println("onScroll() 호출됨 : $v, $v1")
                return true
            }

            fun onLongPress(motionEvent: MotionEvent?) {
                println("onLongPress() 호출됨.")
            }

            fun onFling(motionEvent: MotionEvent?, motionEvent1: MotionEvent?, v: Float, v1: Float): Boolean {
                println("onFling() 호출됨 : $v, $v1")
                return true
            }
        })
        val view2: View = findViewById<View>(R.id.view2)
        view2.setOnTouchListener(object : OnTouchListener() {
            fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
                detector.onTouchEvent(motionEvent)
                return true
            }
        })
    }

    fun println(data: String) {
        textView.append("""
    $data
    
    """.trimIndent())
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "시스템 [BACK] 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }
}
