package com.example.water_time_kt.ui

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.water_time_kt.R
import com.example.water_time_kt.data.DrinkDay
import com.example.water_time_kt.domain.AppDatabase
import com.example.water_time_kt.domain.migration.*
import com.example.water_time_kt.ui.fragments.HistoryFragment
import com.example.water_time_kt.ui.fragments.MainFragment
import com.example.water_time_kt.ui.fragments.SettingsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        val DB_NAME = "database"
        val PREF_TARGET_NAME = "water_target"
        val PREF_TARGET_SIZE = "1700"
        val PREF_TARE = arrayOf("size_tare_1" to "202", "size_tare_2" to "300", "size_tare_3" to "500")
        val PREF_FIRSTRUN = "firstrun"
        val DATE_FORMAT = "dd.MM"
        var waterProgress = 0
        var waterTarget = 0
        var drinkDays: MutableList<DrinkDay> = mutableListOf()
        lateinit var pref: SharedPreferences
    }



    private val coroutineIO = CoroutineScope(Dispatchers.IO)

    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, DB_NAME
        ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toMainFragment: ImageButton = findViewById(R.id.main_fragment_btn)
        toMainFragment.setOnClickListener { toMainFragment() }
        val toHistoryFragment: ImageButton = findViewById(R.id.history_fragment_btn)
        toHistoryFragment.setOnClickListener { toHistoryFragment() }
        val toSettingsFragment: ImageButton = findViewById(R.id.settings_fragment_btn)
        toSettingsFragment.setOnClickListener { toSettingsFragment() }

        supportActionBar!!.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        pref = PreferenceManager.getDefaultSharedPreferences(this)

        if (pref.getBoolean(PREF_FIRSTRUN, true)) { //проверка на первый запуск
            //установка параметров по умолчанию в первый раз
            val ed: Editor = pref.edit()
            ed.putString(PREF_TARGET_NAME, PREF_TARGET_SIZE).apply()
            ed.putString(PREF_TARE[0].first, PREF_TARE[0].second).commit()
            ed.putString(PREF_TARE[1].first, PREF_TARE[1].second).commit()
            ed.putString(PREF_TARE[2].first, PREF_TARE[2].second).commit()
            pref.edit().putBoolean(PREF_FIRSTRUN, false).apply()
        }

        getDataFromDB()
        toMainFragment()
    }

    override fun onStop() {
        updateDB()
        super.onStop()
    }

    fun toMainFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
    }

    fun toHistoryFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, HistoryFragment()).commit()
    }

    fun toSettingsFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, SettingsFragment()).commit()
    }

    private fun getDataFromDB() {
        coroutineIO.launch{
            drinkDays = database.drinkDayDao().getAllDays().toMutableList()
            val today = SimpleDateFormat(DATE_FORMAT).format(Date())

            if (drinkDays.isNullOrEmpty() || today != drinkDays.last().date) { //если даты разные
                drinkDays.add(DrinkDay())
            }
            waterProgress = drinkDays.last().dayResult
        }

    }

    private fun updateDB() {
        coroutineIO.launch {
            if (database.drinkDayDao().getAllDays().size < drinkDays.size) { //если добавился новый день
                database.drinkDayDao().insert(drinkDays.last())
            } else {
                database.drinkDayDao().update(drinkDays.last())
            }
        }
    }
}