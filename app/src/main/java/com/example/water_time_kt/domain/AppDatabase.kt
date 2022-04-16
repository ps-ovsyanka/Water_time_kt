package com.example.water_time_kt.domain;

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.example.water_time_kt.data.DrinkDay;
import com.example.water_time_kt.domain.dao.DrinkDayDao;

@Database(entities = [DrinkDay::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drinkDayDao() : DrinkDayDao
}
