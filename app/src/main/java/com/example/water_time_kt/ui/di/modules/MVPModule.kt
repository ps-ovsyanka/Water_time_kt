package com.example.water_time_kt.ui.di.modules

import android.content.SharedPreferences
import com.example.water_time_kt.domain.dao.DrinkDayDao
import com.example.water_time_kt.ui.MainActivityPresenter
import com.example.water_time_kt.ui.fragments.historyFragment.HistoryFragmentPresenter
import com.example.water_time_kt.ui.fragments.mainFragment.MainFragmentPresenter
import com.example.water_time_kt.ui.fragments.settingsFragment.SettingsFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
class MVPModule {

    @Provides
    fun provideMainActivityPresenter(drinkDayDao: DrinkDayDao, pref: SharedPreferences): MainActivityPresenter =
        MainActivityPresenter(drinkDayDao,  pref)

    @Provides
    fun provideMainFragmentPresenter(drinkDayDao: DrinkDayDao, pref: SharedPreferences): MainFragmentPresenter =
        MainFragmentPresenter(drinkDayDao, pref)

    @Provides
    fun provideHistoryFragmentPresenter(drinkDayDao: DrinkDayDao): HistoryFragmentPresenter =
        HistoryFragmentPresenter(drinkDayDao)

    @Provides
    fun provideSettingsFragmentPresenter(pref: SharedPreferences): SettingsFragmentPresenter =
        SettingsFragmentPresenter(pref)

}