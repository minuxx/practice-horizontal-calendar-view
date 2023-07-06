package com.example.examplecalendarapp

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.time.LocalDate

class OneWeekVPAdapter(
    private val fragmentActivity: FragmentActivity, private val curDate: LocalDate,
    private val onClickListener: IDateClickListener,
): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        return OneWeekFragment(fragmentActivity, position, curDate, onClickListener)
    }
}