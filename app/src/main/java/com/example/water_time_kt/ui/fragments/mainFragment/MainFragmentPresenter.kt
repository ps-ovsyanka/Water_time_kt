package com.example.water_time_kt.ui.fragments.mainFragment

import android.content.SharedPreferences
import com.example.water_time_kt.Application
import com.example.water_time_kt.data.DrinkDay
import com.example.water_time_kt.domain.dao.DrinkDayDao
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARE
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARGET_NAME
import com.example.water_time_kt.ui.MainActivityPresenter.Companion.PREF_TARGET_SIZE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainFragmentPresenter(val drinkDayDao: DrinkDayDao) {

    private var today: DrinkDay = DrinkDay()
    private var drinkItems: MutableList<Int> = mutableListOf()
    private lateinit var view: IMainFragmentView
    private val pref: SharedPreferences = Application.injector.getDependenciesSharedPreferences()
    private val coroutineIO = CoroutineScope(Dispatchers.IO)

    fun onCreate(view: IMainFragmentView){
        this.view = view
        onResume()
    }

    fun onResume(){
        coroutineIO.launch {
            today = drinkDayDao.getAllDays().toMutableList().last()
            drinkItems = today.drinkItems.toMutableList()
        }
        view.setTextProgress(today.dayResult.toString())
        view.setTextTarget(pref.getString(PREF_TARGET_NAME, PREF_TARGET_SIZE))
        view.setProgress(today.dayResult)
        view.apply {
            setTextTare1(pref.getString(PREF_TARE[0].first, PREF_TARE[0].second))
            setTextTare2(pref.getString(PREF_TARE[1].first, PREF_TARE[1].second))
            setTextTare3(pref.getString(PREF_TARE[2].first, PREF_TARE[2].second))
        }
    }

    fun onDestroy(){
        coroutineIO.cancel()
    }

    private fun updateProgress (value: Int){
        today.dayResult +=  value
        today.dayResult.let {
            view.setTextProgress(it.toString())
            view.setProgress(it)
        }
        today.completed = today.dayResult >= pref.getString(PREF_TARGET_NAME, PREF_TARGET_SIZE)!!.toInt()
        today.drinkItems = drinkItems
        coroutineIO.launch {
            drinkDayDao.update(today)
        }
    }

    fun cancelDrinkItem(){
        val value = drinkItems.lastOrNull()?:0
        updateProgress(-value)
        drinkItems.removeLastOrNull()
    }

    fun onClickTare1(){
        val volumeValue = pref.getString(PREF_TARE[0].first, PREF_TARE[0].second)?.toInt()?:0
        updateProgress(volumeValue)
        drinkItems.add(volumeValue)
    }
    fun onClickTare2(){
        val volumeValue = pref.getString(PREF_TARE[1].first, PREF_TARE[1].second)?.toInt()?:0
        updateProgress(volumeValue)
        drinkItems.add(volumeValue)
    }
    fun onClickTare3(){
        val volumeValue = pref.getString(PREF_TARE[2].first, PREF_TARE[2].second)?.toInt()?:0
        updateProgress(volumeValue)
        drinkItems.add(volumeValue)
    }
}