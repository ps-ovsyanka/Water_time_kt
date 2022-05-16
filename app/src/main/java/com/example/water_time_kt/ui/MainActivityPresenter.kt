package com.example.water_time_kt.ui

import android.content.SharedPreferences
import com.example.water_time_kt.Application
import com.example.water_time_kt.data.DrinkDay
import com.example.water_time_kt.domain.dao.DrinkDayDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivityPresenter(private val drinkDayDao: DrinkDayDao, private var pref: SharedPreferences ) {

    companion object {
        const val PREF_FIRSTRUN = "firstrun"
        const val DATE_FORMAT = "dd.MM"
        const val PREF_TARGET_NAME = "water_target"
        const val PREF_TARGET_SIZE = "1700"
        val PREF_TARE = arrayOf("size_tare_1" to "200", "size_tare_2" to "300", "size_tare_3" to "500")
    }

    private lateinit var view: IMainActivityView
    private val coroutineIO = CoroutineScope(Dispatchers.IO)

    fun onCreate(mainView: MainActivity){
        view = mainView
        if (pref.getBoolean(PREF_FIRSTRUN, true)) {
            pref.edit().putString(PREF_TARGET_NAME, PREF_TARGET_SIZE).apply()
            pref.edit().putBoolean(PREF_FIRSTRUN, false).apply()
            PREF_TARE.forEach {
                pref.edit().putString(it.first, it.second).apply()
            }
        }
        getDataFromDB()
        toMainFragment()
    }

    fun onDestroy(){
        coroutineIO.cancel()
    }

    private fun getDataFromDB() {
        coroutineIO.launch{
            val drinkDays = drinkDayDao.getAllDays().toMutableList()
            val today = SimpleDateFormat(DATE_FORMAT).format(Date())

            if (drinkDays.isNullOrEmpty() || today != drinkDays.last().date) {
                drinkDayDao.insert(DrinkDay(date = today))
            }
        }
    }

    fun toMainFragment(){
        view.toMainFragment()
    }

    fun toHistoryFragment(){
        view.toHistoryFragment()
    }

    fun toSettingsFragment(){
        view.toSettingsFragment()
    }
}