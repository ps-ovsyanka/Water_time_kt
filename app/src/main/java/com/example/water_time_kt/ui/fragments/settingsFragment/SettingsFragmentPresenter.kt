package com.example.water_time_kt.ui.fragments.settingsFragment

import android.content.SharedPreferences
import com.example.water_time_kt.Application
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARE
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARGET_NAME
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARGET_SIZE

class SettingsFragmentPresenter {

    private lateinit var view: ISettingsFragmentView
    companion object{
        val pref: SharedPreferences = Application.injector.getDependenciesSharedPreferences()
    }

    fun onPause(){
        pref.edit().apply {
            putString(PREF_TARGET_NAME, view.getTextTarget()).apply()
            putString(PREF_TARE[0].first, view.getTextTare1()).apply()
            putString(PREF_TARE[1].first, view.getTextTare2()).apply()
            putString(PREF_TARE[2].first, view.getTextTare3()).apply()
        }
    }

    fun onCreate(settingView: SettingsFragment) {
        view = settingView
        view.setEditTextTarget(pref.getString(PREF_TARGET_NAME, PREF_TARGET_SIZE))
        view.setEditTextTare1(pref.getString(PREF_TARE[0].first, PREF_TARE[0].second))
        view.setEditTextTare2(pref.getString(PREF_TARE[1].first, PREF_TARE[1].second))
        view.setEditTextTare3(pref.getString(PREF_TARE[2].first, PREF_TARE[2].second))
    }
}