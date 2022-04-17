package com.example.water_time_kt.data

import androidx.room.*
import com.example.water_time_kt.domain.DrinkItemsTypeConverters
import com.example.water_time_kt.ui.MainActivity
import java.text.SimpleDateFormat
import java.util.*
@TypeConverters(DrinkItemsTypeConverters::class)
@Entity(tableName = "DrinkDays")
class DrinkDay(@PrimaryKey(autoGenerate = true)
               val id : Long = 0,
               var dayResult : Int = 0,
               var completed : Boolean = false,
               var date: String = "",
               var description: String = "" ) {
    init {
        date = SimpleDateFormat(MainActivity.DATE_FORMAT).format(Date())
    }

    @field:TypeConverters(DrinkItemsTypeConverters::class)
    var drinkItems: List<Int> = listOf() //список приемов воды
}

