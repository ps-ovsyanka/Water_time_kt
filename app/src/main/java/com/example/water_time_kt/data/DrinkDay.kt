package com.example.water_time_kt.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.example.water_time_kt.domain.DrinkItemsTypeConverters
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "DrinkDays")
class DrinkDay(@PrimaryKey(autoGenerate = true)
               val id : Long = 0,
               var dayResult : Int = 0,
               var completed : Boolean = false) {
    val date = SimpleDateFormat("dd.MM").format(Date())

    @TypeConverters(DrinkItemsTypeConverters::class)
    var drinkItems: MutableList<Int> = ArrayList() //список приемов воды
}