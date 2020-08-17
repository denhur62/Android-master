package com.example.databasepractice2

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var editText: EditText? = null
    var editText2: EditText? = null
    var textView: TextView? = null
    var dbHelper: DatabaseHelper? = null
    var database: SQLiteDatabase? = null
    var tableName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        editText2 = findViewById(R.id.editText2)
        textView = findViewById(R.id.textView)

        button.setOnClickListener {
            val databaseName = editText?.getText().toString()
            createDatabase(databaseName)
        }

        button2.setOnClickListener {
            tableName = editText2?.getText().toString()
            createTable(tableName!!)
            insertRecord()
        }
        button3.setOnClickListener { executeQuery() }
    }

    private fun createDatabase(name: String) {
        dbHelper = DatabaseHelper(this)
        database = dbHelper!!.getWritableDatabase()
        println("데이터베이스 생성함 : $name")
    }

    private fun createTable(name: String) {

        if (database == null) {
            println("데이터베이스를 먼저 생성하세요.")
            return
        }
        database!!.execSQL("create table if not exists " + name + "("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " age integer, "
                + " mobile text)")
        println("테이블 생성함 : $name")
    }

    private fun insertRecord() {
        println("insertRecord 호출됨.")
        if (database == null) {
            println("데이터베이스를 먼저 생성하세요.")
            return
        }
        if (tableName == null) {
            println("테이블을 먼저 생성하세요.")
            return
        }
        database!!.execSQL("insert into " + tableName
                + "(name, age, mobile) "
                + " values "
                + "('John', 20, '010-1000-1000')")

    }

    fun println(data: String) {
        textView!!.append("""$data""".trimIndent()) }

    fun executeQuery() {
        println("executeQuery 호출됨.")
        var cursor = database!!.rawQuery("select _id, name, age, mobile from emp", null)
        var recordCount = cursor.count
        println("레코드 개수 : $recordCount")
        for (i in 0 until recordCount) {
            cursor.moveToNext()
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val age = cursor.getInt(2)
            val mobile = cursor.getString(3)
            println("레코드 #$i : $id, $name, $age, $mobile")
        }
        cursor.close()
    }
}
