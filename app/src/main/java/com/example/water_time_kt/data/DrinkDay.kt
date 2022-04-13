package com.example.water_time_kt.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "DrinkDays")
class DrinkDay(@PrimaryKey(autoGenerate = true)
               val id : Long = 0,
               var dayResult : Int = 0,
               var completed : Boolean = false) {
    val date = SimpleDateFormat("dd.MM").format(Date())

    @Relation(parentColumn = "id", entityColumn = "day_id")
    var drinkItems: MutableList<Int> = ArrayList() //список приемов воды
}