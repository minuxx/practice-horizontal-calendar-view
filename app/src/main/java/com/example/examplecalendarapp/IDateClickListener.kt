package com.example.examplecalendarapp

import java.time.LocalDate

interface IDateClickListener {
    fun onClickDate(date: LocalDate)
}