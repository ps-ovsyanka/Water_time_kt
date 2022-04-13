package com.example.water_time_kt.domain

import androidx.room.TypeConverter
import java.util.*
import java.util.stream.Collectors


class DrinkItemsTypeConverters {
    @TypeConverter
    fun fromHobbies(drinkItems: List<Int>): String? {
        return drinkItems.joinToString()
    }

    @TypeConverter
    fun toHobbies(data: String): List<String>? {
        return Arrays.asList(*data.split(",").toTypedArray())
    }
}