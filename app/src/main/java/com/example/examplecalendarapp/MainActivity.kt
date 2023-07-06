package com.example.examplecalendarapp

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.examplecalendarapp.databinding.ActivityMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity(), IDateClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: OneWeekVPAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOneWeekViewPager()
    }

    private fun setOneWeekViewPager() {
        saveSelectedDate(LocalDate.now())
        adapter = OneWeekVPAdapter(this, LocalDate.now(), this)
        binding.homeWeeklyCalendarWeekVp.adapter = adapter
        binding.homeWeeklyCalendarWeekVp.setCurrentItem(Int.MAX_VALUE / 2, false)
    }

    override fun onClickDate(date: LocalDate) {
        saveSelectedDate(date)
        // API 호출
    }

    private fun saveSelectedDate(date: LocalDate) {
        val sharedPreference = getSharedPreferences("CALENDAR-APP", MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreference.edit()
        editor.putString("SELECTED-DATE", date.toString())
        editor.apply()
    }
}