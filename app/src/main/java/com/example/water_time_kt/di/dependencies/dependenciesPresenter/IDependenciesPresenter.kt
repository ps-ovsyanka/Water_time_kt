package com.example.water_time_kt.di.dependencies.dependenciesPresenter

import com.example.water_time_kt.ui.MainActivityPresenter
import com.example.water_time_kt.ui.fragments.historyFragment.HistoryFragmentPresenter
import com.example.water_time_kt.ui.fragments.mainFragment.MainFragmentPresenter
import com.example.water_time_kt.ui.fragments.settingsFragment.SettingsFragmentPresenter

interface IDependenciesPresenter {
    fun getMainActivityPresenter(): MainActivityPresenter
    fun getMainFragmentPresenter(): MainFragmentPresenter
    fun getSettingsFragmentPresenter(): SettingsFragmentPresenter
    fun getHistoryFragmentPresenter(): HistoryFragmentPresenter
}