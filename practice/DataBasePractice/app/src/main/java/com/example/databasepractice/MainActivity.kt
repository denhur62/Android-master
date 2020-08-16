package com.example.databasepractice

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var database : SQLiteDatabase?=null
    var tableName :String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener({
            var databaseName=editText.text.toString()
            createDatabase(databaseName)})
        button2.setOnClickListener({
            var tableName=editText2.text.toString()
            createTable(tableName)
            insertRecord()})
    }
    private fun createDatabase(name: String) {
        println("createDatabase 호출됨.")
        database = openOrCreateDatabase(name, Context.MODE_PRIVATE, null)
        println("데이터베이스 생성함 : $name")
    }

    private fun createTable(name: String) {
        println("createTable 호출됨.")
        if (database == null) {
            println("데이터베이스를 먼저 생성하세요.")
            return
        }
        database?.execSQL("create table if not exists " + name + "("
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
        println("레코드 추가함.")
    }

    fun println(data: String) {
        textView.append("""$data""".trimIndent())
    }
}