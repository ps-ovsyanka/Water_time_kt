package com.example.water_time_kt

import android.app.Application
import com.example.water_time_kt.ui.di.ApplicationComponent
import com.example.water_time_kt.ui.di.DaggerApplicationComponent
import com.example.water_time_kt.ui.di.modules.ContextModule

class Application: Application() {

    companion object{
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
//        injector =ImplInjector
//            .Builder()
//            .dependencySharedPreferences(applicationContext)
//            .dependencyApiDatabase(applicationContext)
//            .dependencyDrinkDayDao()
//            .build()
    }


}