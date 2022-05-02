package com.example.water_time_kt.ui.fragments.mainFragment

import android.content.SharedPreferences
import com.example.water_time_kt.Application
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARE
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARGET_NAME
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARGET_SIZE
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.drinkDays
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.waterProgress

class MainFragmentPresenter {

    private lateinit var drinkItems : MutableList<Int>
    private var pref: SharedPreferences = Application.injector.getDependenciesSharedPreferences()
    private lateinit var view: IMainFragmentView

    fun onCreate(view: IMainFragmentView){
        this.view = view
        drinkItems = drinkDays.lastOrNull()?.drinkItems?.toMutableList()?: mutableListOf()
    }

    fun onResume(){
        drinkDays.lastOrNull()?.drinkItems = drinkItems
        view.setTextProgress(waterProgress.toString())
        view.setTextTarget(pref.getString(PREF_TARGET_NAME, PREF_TARGET_SIZE))
        view.setProgress(waterProgress)
        view.apply {
            setTextTare1(pref.getString(PREF_TARE[0].first, PREF_TARE[0].second))
            setTextTare2(pref.getString(PREF_TARE[1].first, PREF_TARE[1].second))
            setTextTare3(pref.getString(PREF_TARE[2].first, PREF_TARE[2].second))
        }
    }


    fun updateProgress (value: Int){
        waterProgress +=  value
        view.setTextProgress(waterProgress.toString())
        view.setProgress(waterProgress)
        drinkDays.lastOrNull()?.dayResult = waterProgress
        drinkDays.lastOrNull()?.completed = drinkDays.last().dayResult >= pref.getString(PREF_TARGET_NAME, PREF_TARGET_SIZE)?.toInt()?:PREF_TARGET_SIZE.toInt()
    }

    fun cancelDrinkItem(){
        val value = drinkItems.lastOrNull()?: 0
        updateProgress(-value)
        drinkItems.removeLastOrNull()
    }

    fun onClickTare1(){
        val volumValue = pref.getString(PREF_TARE[0].first, PREF_TARE[0].second)?.toInt()?:0
        updateProgress(volumValue)
        drinkItems.add(volumValue)
    }
    fun onClickTare2(){
        val volumValue = pref.getString(PREF_TARE[1].first, PREF_TARE[1].second)?.toInt()?:0
        updateProgress(volumValue)
        drinkItems.add(volumValue)
    }
    fun onClickTare3(){
        val volumValue = pref.getString(PREF_TARE[2].first, PREF_TARE[2].second)?.toInt()?:0
        updateProgress(volumValue)
        drinkItems.add(volumValue)
    }
}