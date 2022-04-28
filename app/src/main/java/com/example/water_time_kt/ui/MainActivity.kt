package com.example.water_time_kt.ui

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.water_time_kt.Application
import com.example.water_time_kt.R
import com.example.water_time_kt.data.DrinkDay
import com.example.water_time_kt.domain.dao.DrinkDayDao
import com.example.water_time_kt.ui.fragments.HistoryFragment
import com.example.water_time_kt.ui.fragments.MainFragment
import com.example.water_time_kt.ui.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val PREF_FIRSTRUN = "firstrun"
        const val DATE_FORMAT = "dd.MM"
        const val PREF_TARGET_NAME = "water_target"
        const val PREF_TARGET_SIZE = "1700"
        val PREF_TARE = arrayOf("size_tare_1" to "202", "size_tare_2" to "300", "size_tare_3" to "500")
        var waterProgress = 0
        var drinkDays: MutableList<DrinkDay> = mutableListOf()
        var pref: SharedPreferences = Application.injector.getDependenciesSharedPreferences()
        val drinkDayDao: DrinkDayDao = Application.injector.getDependenciesDrinkDayDao()
    }

    private val coroutineIO = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_fragment_btn.setOnClickListener { toMainFragment() }
        history_fragment_btn.setOnClickListener { toHistoryFragment() }
        settings_fragment_btn.setOnClickListener { toSettingsFragment() }

        supportActionBar!!.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

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

    override fun onStop() {
        updateDB()
        super.onStop()
    }

    private fun toMainFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
    }

    private fun toHistoryFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, HistoryFragment()).commit()
    }

    private fun toSettingsFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, SettingsFragment()).commit()
    }

    private fun getDataFromDB() {
        coroutineIO.launch{
            drinkDays = Application.injector.getDependenciesDrinkDayDao().getAllDays().toMutableList()
            val today = SimpleDateFormat(DATE_FORMAT).format(Date())

            if (drinkDays.isNullOrEmpty() || today != drinkDays.last().date) { //если даты разные
                drinkDays.add(DrinkDay(date = today))
            }
            waterProgress = drinkDays.last().dayResult
        }

    }

    private fun updateDB() {
        coroutineIO.launch {
            if (drinkDayDao.getAllDays().size < drinkDays.size) { //если добавился новый день
                drinkDayDao.insert(drinkDays.last())
            } else {
                drinkDayDao.update(drinkDays.last())
            }
        }
    }
}