package com.example.water_time_kt.ui.fragments.settingsFragment

import android.content.SharedPreferences
import com.example.water_time_kt.Application
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARE
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARGET_NAME
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARGET_SIZE
import javax.inject.Inject

class SettingsFragmentPresenter @Inject constructor(private val pref: SharedPreferences) {

    private lateinit var view: ISettingsFragmentView

    fun onCreate(settingView: SettingsFragment) {
        view = settingView
        view.setEditTextTarget(pref.getString(PREF_TARGET_NAME, PREF_TARGET_SIZE))
        view.setEditTextTare1(pref.getString(PREF_TARE[0].first, PREF_TARE[0].second))
        view.setEditTextTare2(pref.getString(PREF_TARE[1].first, PREF_TARE[1].second))
        view.setEditTextTare3(pref.getString(PREF_TARE[2].first, PREF_TARE[2].second))
    }

    fun setTargetSize(size: String){
        pref.edit().putString(PREF_TARGET_NAME, size).apply()
    }

    fun setTareOneSize(size: String){
        pref.edit().putString(PREF_TARE[0].first, size).apply()
    }

    fun setTareTwoSize(size: String){
        pref.edit().putString(PREF_TARE[1].first, size).apply()
    }

    fun setTareThreeSize(size: String){
        pref.edit().putString(PREF_TARE[2].first, size).apply()
    }
}