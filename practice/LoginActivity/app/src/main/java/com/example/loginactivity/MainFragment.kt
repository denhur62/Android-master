package com.example.loginactivity

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {
    var selectedDateget: Date? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false) as ViewGroup
        var saveButton=rootView.findViewById<Button>(R.id.saveButton)
        var birthButton=rootView.findViewById<Button>(R.id.birthButton)
        birthButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                showDateDialog()
            }
        })
        saveButton.setOnClickListener {
            val nameStr = nameInput.getText().toString()
            val ageStr = ageInput.getText().toString()
            val birthStr = birthButton.getText().toString()
            Toast.makeText(context, "이름 : $nameStr, 나이 : $ageStr, 생년월일 : $birthStr", Toast.LENGTH_SHORT).show()
        }
        // set selected date using current date
        val curDate = Date()
        setSelectedDate(curDate)
        val selectedDateStr = dateFormat.format(curDate)
        birthButton.text = selectedDateStr


        return rootView
    }

    private fun showDateDialog() {
        val birthDateStr = birthButton!!.text.toString()
        val calendar = Calendar.getInstance()
        var curBirthDate: Date? = Date()
        try { curBirthDate = dateFormat.parse(birthDateStr)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        calendar.time = curBirthDate
        val curYear = calendar[Calendar.YEAR]
        val curMonth = calendar[Calendar.MONTH]
        val curDay = calendar[Calendar.DAY_OF_MONTH]
        val dialog = DatePickerDialog(context!!, birthDateSetListener, curYear, curMonth, curDay)
        dialog.show()
    }

    private val birthDateSetListener = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val selectedCalendar = Calendar.getInstance()
            selectedCalendar[Calendar.YEAR] = year
            selectedCalendar[Calendar.MONTH] = monthOfYear
            selectedCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val curDate = selectedCalendar.time
            setSelectedDate(curDate)
        }

     fun setSelectedDate(curDate: Date) {
        selectedDateget = curDate
        val selectedDateStr = dateFormat.format(curDate)
         var birthButton=view?.findViewById<Button>(R.id.birthButton)
         birthButton?.text = selectedDateStr
    }

    companion object {
        @JvmField
        var dateFormat = SimpleDateFormat("yyyy년 MM월 dd일")
    }
}