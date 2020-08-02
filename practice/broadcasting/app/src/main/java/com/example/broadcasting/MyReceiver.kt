package com.example.broadcasting

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class MyReceiver : BroadcastReceiver() {
    private val TAG = "SmsReceiver"
    var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "onReceive() 메서드 호출됨.")
        val bundle = intent.extras
        val messages = parseSmsMessage(bundle)
        if (messages != null && messages.size > 0) {
            val sender = messages[0]!!.originatingAddress
            Log.i(TAG, "SMS sender : $sender")
            val contents = messages[0]!!.messageBody
            Log.i(TAG, "SMS contents : $contents")
            val receivedDate = Date(messages[0]!!.timestampMillis)
            Log.i(TAG, "SMS received date : $receivedDate")
            sendToActivity(context, sender, contents, receivedDate)
        }
    }

    private fun parseSmsMessage(bundle: Bundle?): Array<SmsMessage?>? {
        val objs = bundle!!["pdus"] as Array<Any>?
        val messages = arrayOfNulls<SmsMessage>(objs!!.size)
        val smsCount = objs.size
        for (i in 0 until smsCount) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val format = bundle.getString("format")
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
            } else {
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
            }
        }
        return messages
    }

    private fun sendToActivity(context: Context, sender: String?, contents: String, receivedDate: Date) {
        val myIntent = Intent(context, SmsActivity::class.java)
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        myIntent.putExtra("sender", sender)
        myIntent.putExtra("contents", contents)
        myIntent.putExtra("receivedDate", format.format(receivedDate))
        context.startActivity(myIntent)
    }

}
