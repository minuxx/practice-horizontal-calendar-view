package com.example.examplecalendarapp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.examplecalendarapp.databinding.FragmentOneWeekBinding
import java.time.LocalDate

class OneWeekFragment(
    private val context: Context,
    private val position: Int,
    private val curDate: LocalDate,
    private val onClickListener: IDateClickListener,
) : Fragment(){
    private lateinit var binding : FragmentOneWeekBinding
    private val todayPosition = Int.MAX_VALUE / 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneWeekBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstance: Bundle?){
        super.onViewCreated(view, savedInstance)

        val newDate: LocalDate = if (position < todayPosition){
           curDate.minusDays(((todayPosition - position) * 7).toLong())
        } else if (position > todayPosition){
            curDate.plusDays(((position - todayPosition) * 7).toLong())
        } else {
            curDate
        }

        val dates = calculateDatesOfWeek(newDate)

        setDateTextView(binding.tv1, dates[0])
        setDateTextView(binding.tv2, dates[1])
        setDateTextView(binding.tv3, dates[2])
        setDateTextView(binding.tv4, dates[3])
        setDateTextView(binding.tv5, dates[4])
        setDateTextView(binding.tv6, dates[5])
        setDateTextView(binding.tv7, dates[6])
    }


    override fun onResume() {
        super.onResume()

        val sharedPreference = context.getSharedPreferences("CALENDAR-APP", MODE_PRIVATE)
        val selectedDate = sharedPreference.getString("SELECTED-DATE", "")

        when(selectedDate.toString()) {
            binding.tv1.text -> binding.tv1.setTextColor(Color.WHITE)
            binding.tv2.text -> binding.tv2.setTextColor(Color.WHITE)
            binding.tv3.text -> binding.tv3.setTextColor(Color.WHITE)
            binding.tv4.text -> binding.tv4.setTextColor(Color.WHITE)
            binding.tv5.text -> binding.tv5.setTextColor(Color.WHITE)
            binding.tv6.text -> binding.tv6.setTextColor(Color.WHITE)
            binding.tv7.text -> binding.tv7.setTextColor(Color.WHITE)
        }
    }

    override fun onPause() {
        super.onPause()
        resetUi()
    }

    private fun setDateTextView(textView: TextView, date: LocalDate) {
        textView.text = date.toString()
        textView.setOnClickListener{
            resetUi()
            onClickListener.onClickDate(date)
            textView.setTextColor(Color.WHITE)
        }
    }

    private fun resetUi() {
        binding.tv1.setTextColor(Color.BLACK)
        binding.tv2.setTextColor(Color.BLACK)
        binding.tv3.setTextColor(Color.BLACK)
        binding.tv4.setTextColor(Color.BLACK)
        binding.tv5.setTextColor(Color.BLACK)
        binding.tv6.setTextColor(Color.BLACK)
        binding.tv7.setTextColor(Color.BLACK)
    }

    private fun calculateDatesOfWeek(date: LocalDate): List<LocalDate> {
        val dates = ArrayList<LocalDate>()
        val dayOfToday = date.dayOfWeek.value - 1

        if(dayOfToday == 6) {
            for (i in 0..dayOfToday){
                dates.add(date.plusDays(i.toLong()))
            }
        } else {
            for (i in 0 .. dayOfToday){
                dates.add(date.minusDays((dayOfToday + 1 - i).toLong()))
            }
            for (i in dayOfToday .. 6){
                dates.add(date.plusDays((i - dayOfToday).toLong()))
            }
        }

        return dates
    }
}