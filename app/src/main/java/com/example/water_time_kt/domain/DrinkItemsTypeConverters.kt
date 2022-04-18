package com.example.water_time_kt.domain

import androidx.room.TypeConverter
import java.util.*
import java.util.stream.Collectors


class DrinkItemsTypeConverters {
    @TypeConverter
    fun fromDrinkItems(drinkItems: List<Int>): String? {
        return drinkItems.joinToString(",")
    }

    @TypeConverter
    fun toDrinkItems(data: String): List<Int>? {
        if (data == "") return listOf()
        return Arrays.asList(*data.split(",").toTypedArray()).map { s -> s.toInt() }
    }
}