package com.example.water_time_kt.ui.di

import com.example.water_time_kt.ui.MainActivity
import com.example.water_time_kt.ui.di.modules.DatabaseModule
import com.example.water_time_kt.ui.di.modules.MVPModule
import com.example.water_time_kt.ui.di.modules.SharedPreferenceModule
import com.example.water_time_kt.ui.fragments.historyFragment.HistoryFragment
import com.example.water_time_kt.ui.fragments.mainFragment.MainFragment
import com.example.water_time_kt.ui.fragments.settingsFragment.SettingsFragment
import dagger.Component

@Component(modules = [DatabaseModule::class, MVPModule::class, SharedPreferenceModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(mainFragment: MainFragment)

    fun inject(historyFragment: HistoryFragment)

    fun inject(settingsFragment: SettingsFragment)
}