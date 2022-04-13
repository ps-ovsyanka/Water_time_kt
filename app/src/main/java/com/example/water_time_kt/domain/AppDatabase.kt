package com.example.water_time_kt.domain;

import android.arch.persistence.room.*;

import com.example.water_time_kt.data.DrinkDay;
import com.example.water_time_kt.domain.dao.DrinkDayDao;

@Database(entities = [DrinkDay::class], version = 1)
public abstract class AppDatabase : RoomDatabase() {
    abstract fun drinkDayDao() : DrinkDayDao
}
