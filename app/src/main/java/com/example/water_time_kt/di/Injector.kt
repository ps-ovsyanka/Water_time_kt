package com.example.water_time_kt.di

import android.content.SharedPreferences
import androidx.room.RoomDatabase
import com.example.water_time_kt.domain.dao.DrinkDayDao

interface Injector {
    fun getDependenciesSharedPreferences() : SharedPreferences
    fun getDependenciesApiDatabase() : RoomDatabase
    fun getDependenciesDrinkDayDao() : DrinkDayDao
}