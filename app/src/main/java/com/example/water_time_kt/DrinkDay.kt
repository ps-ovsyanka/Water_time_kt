package com.example.water_time_kt

import java.text.SimpleDateFormat
import java.util.*

class DrinkDay(val dayResult: Int, val completed:Boolean) {
    val date = SimpleDateFormat("dd.MM").format(Date())
}