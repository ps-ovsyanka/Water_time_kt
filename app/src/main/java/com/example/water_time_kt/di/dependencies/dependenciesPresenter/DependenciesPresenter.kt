package com.example.water_time_kt.di.dependencies.dependenciesPresenter

import com.example.water_time_kt.ui.MainActivityPresenter
import com.example.water_time_kt.ui.fragments.historyFragment.HistoryFragmentPresenter
import com.example.water_time_kt.ui.fragments.mainFragment.MainFragmentPresenter
import com.example.water_time_kt.ui.fragments.settingsFragment.SettingsFragmentPresenter

class DependenciesPresenter(
    val mainActivityPresenter: DependenciesMainActivityPresenter,
    val mainFragmentPresenter: DependenciesMainFragmentPresenter,
    val settingsFragmentPresenter: DependenciesSettingsFragmentPresenter,
    val historyFragmentPresenter: DependenciesHistoryFragmentPresenter
): IDependenciesPresenter {

    override fun getMainActivityPresenter(): MainActivityPresenter = mainActivityPresenter.presenter
    override fun getMainFragmentPresenter(): MainFragmentPresenter = mainFragmentPresenter.presenter
    override fun getSettingsFragmentPresenter(): SettingsFragmentPresenter = settingsFragmentPresenter.presenter
    override fun getHistoryFragmentPresenter(): HistoryFragmentPresenter = historyFragmentPresenter.presenter
}