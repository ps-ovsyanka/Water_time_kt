package com.example.water_time_kt.ui.di.modules

import android.content.Context
import androidx.room.Room
import com.example.water_time_kt.domain.AppDatabase
import com.example.water_time_kt.domain.dao.DrinkDayDao
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Provides
    fun providesDao(context : Context) : DrinkDayDao =
        Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .build().drinkDayDao()

}