package com.example.water_time_kt.domain;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.TypeConverters;

import com.example.water_time_kt.data.DrinkDay;
import com.example.water_time_kt.domain.dao.DrinkDayDao;

@Database(entities = {DrinkDay.class}, version = 1)
abstract public class AppDatabase {
    private static AppDatabase INSTANCE;
    public abstract DrinkDayDao userDao();
}
