package com.example.water_time_kt

import android.app.Application
import com.example.water_time_kt.di.ImplInjector
import com.example.water_time_kt.di.Injector

class Application: Application() {

    companion object{
        lateinit var injector: Injector
    }

    override fun onCreate() {
        super.onCreate()
        injector =ImplInjector
            .Builder()
            .dependencySharedPreferences(applicationContext)
            .dependenciesApiDatabase(applicationContext)
            .dependenciesDrinkDayDao()
            .build()
    }

}