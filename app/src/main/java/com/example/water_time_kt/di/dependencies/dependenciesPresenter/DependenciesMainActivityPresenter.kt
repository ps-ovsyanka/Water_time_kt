package com.example.water_time_kt.di.dependencies.dependenciesPresenter

import com.example.water_time_kt.domain.dao.DrinkDayDao
import com.example.water_time_kt.ui.MainActivityPresenter

class DependenciesMainActivityPresenter(val drinkDayDao: DrinkDayDao) {
    val presenter: MainActivityPresenter

    init {
        presenter = MainActivityPresenter(drinkDayDao)
    }
}
