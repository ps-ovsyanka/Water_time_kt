package com.example.water_time_kt.di.dependencies.dependenciesPresenter

import com.example.water_time_kt.domain.dao.DrinkDayDao
import com.example.water_time_kt.ui.fragments.mainFragment.MainFragmentPresenter

class DependenciesMainFragmentPresenter(val drinkDayDao: DrinkDayDao) {
    val presenter: MainFragmentPresenter = MainFragmentPresenter(drinkDayDao)
}
