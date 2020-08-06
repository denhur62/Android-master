package com.example.socketexample

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket

class MainActivity : AppCompatActivity() {
    var handler= Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "시작")
        button.setOnClickListener(View.OnClickListener {
            val data = editText.getText().toString()
            Thread(Runnable { send(data) }).start()
        })

        button2.setOnClickListener { Thread(Runnable { startServer() }).start() }

}
    fun send(data: String?) {
        try {
            val portNumber = 5001
            val sock = Socket("localhost", portNumber)
            printClientLog("소켓 연결함.")
            val outstream = ObjectOutputStream(sock.getOutputStream())
            outstream.writeObject(data)
            outstream.flush()

            printClientLog("데이터 전송함.")
            val instream = ObjectInputStream(sock.getInputStream())
            printClientLog("서버로부터 받음 : " + instream.readObject())
            sock.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
    fun startServer() {
        try {
            val portNumber = 5001
            val server = ServerSocket(portNumber)
            printServerLog("서버 시작함 : $portNumber")

            while (true) {
                val sock = server.accept()
                val clientHost = sock.localAddress
                val clientPort = sock.port
                printServerLog("클라이언트 연결됨 : $clientHost : $clientPort")
                val instream = ObjectInputStream(sock.getInputStream())
                val obj = instream.readObject()
                printServerLog("데이터 받음 : $obj")
                val outstream = ObjectOutputStream(sock.getOutputStream())
                outstream.writeObject("$obj from Server.")
                outstream.flush()
                printServerLog("데이터 보냄.")
                sock.close()
            }
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
    }
    fun printClientLog(data: String) {
        Log.d("MainActivity", data)
        handler.post(Runnable {
            textView.append("""$data""".trimIndent())
        })
    }

    fun printServerLog(data: String) {
        Log.d("MainActivity", data)
        handler.post(Runnable { textView2.append("""$data""".trimIndent())
        })
    }

}