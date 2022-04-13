package com.example.water_time_kt.data

import android.arch.persistence.room.Entity
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "DrinkDays")
class DrinkDay() {

    val id : Long = 0
    val dayResult : Int = 0
    val completed : Boolean = false
    val date = SimpleDateFormat("dd.MM").format(Date())
}