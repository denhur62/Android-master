package com.example.example5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var abar: ActionBar? = null
     override fun onCreate (savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
          abar = getSupportActionBar()

         button.setOnClickListener(object : View.OnClickListener {
             override fun onClick(v: View?) {
                 abar?.setLogo(R.drawable.home)
                 abar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME or ActionBar.DISPLAY_USE_LOGO)
             }
         })

     }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val curId: Int = item.getItemId()
        when (curId) {
            R.id.menu_refresh -> Toast.makeText(this, "새로고침 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show()
            R.id.menu_search -> Toast.makeText(this, "검색 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show()
            R.id.menu_settings -> Toast.makeText(this, "설정 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show()
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
