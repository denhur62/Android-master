package org.techtown.mission03

import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var imageView01: ImageView? = null
    var imageView02: ImageView? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView01 = findViewById(R.id.imageView01)
        imageView02 = findViewById(R.id.imageView02)
        val button01: Button = findViewById(R.id.button01)
        button01.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View?) {
                moveImageUp()
            }
        })
        val button02: Button = findViewById(R.id.button02)
        button02.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View?) {
                moveImageDown()
            }
        })
        moveImageUp()
    }

    private fun moveImageDown() {
        imageView01.setImageResource(0)
        imageView02.setImageResource(R.drawable.beach)
        imageView01.invalidate()
        imageView02.invalidate()
    }

    private fun moveImageUp() {
        imageView01.setImageResource(R.drawable.beach)
        imageView02.setImageResource(0)
        imageView01.invalidate()
        imageView02.invalidate()
    }
}