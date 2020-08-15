package com.example.contentprovider

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener { insertPerson() }
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener { queryPerson() }
        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener { updatePerson() }
        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener { deletePerson() }
    }

    fun insertPerson() {
        println("insertPerson 호출됨")
        val uriString = "content://org.techtown.provider/person"
        var uri = Uri.parse(uriString)
        val cursor = contentResolver.query(uri!!, null, null, null, null)
        val columns = cursor!!.columnNames
        println("columns count -> " + columns.size)
        for (i in columns.indices) {
            println("#" + i + " : " + columns[i])
        }
        val values = ContentValues()
        values.put("name", "john")
        values.put("age", 20)
        values.put("mobile", "010-1000-1000")
        uri = contentResolver.insert(uri, values)
        println("insert 결과 -> $uri")
    }

    fun queryPerson() {
        try {
            val uriString = "content://org.techtown.provider/person"
            val uri = Uri.parse(uriString)
            val columns = arrayOf("name", "age", "mobile")
            val cursor = contentResolver.query(uri, columns, null, null, "name ASC")
            println("query 결과 : " + cursor!!.count)
            var index = 0
            while (cursor.moveToNext()) {
                val name = cursor.getString(cursor.getColumnIndex(columns[0]))
                val age = cursor.getInt(cursor.getColumnIndex(columns[1]))
                val mobile = cursor.getString(cursor.getColumnIndex(columns[2]))
                println("#$index -> $name, $age, $mobile")
                index += 1
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updatePerson() {
        val uriString = "content://org.techtown.provider/person"
        val uri = Uri.parse(uriString)
        val selection = "mobile = ?"
        val selectionArgs = arrayOf("010-1000-1000")
        val updateValue = ContentValues()
        updateValue.put("mobile", "010-2000-2000")
        val count = contentResolver.update(uri, updateValue, selection, selectionArgs)
        println("update 결과 : $count")
    }

    fun deletePerson() {
        val uriString = "content://org.techtown.provider/person"
        val uri = Uri.parse(uriString)
        val selection = "name = ?"
        val selectionArgs = arrayOf("john")
        val count = contentResolver.delete(uri, selection, selectionArgs)
        println("delete 결과 : $count")
    }

    fun println(data: String) {
        textView!!.append("""
    $data
    
    """.trimIndent())
    }
}
