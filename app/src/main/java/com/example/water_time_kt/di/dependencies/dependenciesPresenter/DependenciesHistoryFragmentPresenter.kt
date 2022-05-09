package com.example.water_time_kt.di.dependencies.dependenciesPresenter

import com.example.water_time_kt.domain.dao.DrinkDayDao
import com.example.water_time_kt.ui.fragments.historyFragment.HistoryFragmentPresenter

class DependenciesHistoryFragmentPresenter(val drinkDayDao: DrinkDayDao) {
    val presenter: HistoryFragmentPresenter = HistoryFragmentPresenter(drinkDayDao)
}
