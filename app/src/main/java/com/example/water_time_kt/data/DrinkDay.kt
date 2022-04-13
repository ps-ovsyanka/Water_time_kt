package com.example.water_time_kt.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "DrinkDays")
class DrinkDay(@PrimaryKey(autoGenerate = true)
               val id : Long = 0,
               var dayResult : Int = 0,
               var completed : Boolean = false) {
    val date = SimpleDateFormat("dd.MM").format(Date())


}