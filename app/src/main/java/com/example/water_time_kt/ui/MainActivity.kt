package com.example.water_time_kt.ui

import android.arch.persistence.room.Room
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.AppBarConfiguration.Builder
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.water_time_kt.R
import com.example.water_time_kt.data.DrinkDay
import com.example.water_time_kt.domain.AppDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    val DB_NAME = "database"
    val TARGET = "water_target"
    val SIZE_TARE_1 = "size_tare_1"
    val SIZE_TARE_2 = "size_tare_2"
    val SIZE_TARE_3 = "size_tare_3"

    var waterProgress = 0
    var waterTarget = 0
    lateinit var drinkDays: MutableList<DrinkDay>

    val pref: SharedPreferences = getPreferences(android.content.Context.MODE_PRIVATE)

    private val coroutineIO = CoroutineScope(Dispatchers.IO)

    val database = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, DB_NAME
    ).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        val appBarConfiguration: AppBarConfiguration =
            Builder(R.id.navigation_history, R.id.navigation_main, R.id.navigation_setting).build()

        val navController = findNavController(this, R.id.nav_host_fragment)
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        setupWithNavController(navView, navController)

        supportActionBar!!.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        if (pref.getBoolean("firstrun", true)) { //проверка на первый запуск
            //установка парапетров по умолчанию в первый раз
            val ed: Editor = pref.edit()
            ed.putString(TARGET, "1700").commit()
            ed.putString(SIZE_TARE_1, "200").commit()
            ed.putString(SIZE_TARE_2, "300").commit()
            ed.putString(SIZE_TARE_3, "500").commit()
            pref.edit().putBoolean("firstrun", false).commit()
        }
        getDataFromDB()
    }

    override fun onStop() {
        updateDB()
        super.onStop()
    }


    private fun getDataFromDB() {
        coroutineIO.launch{
            drinkDays = database.drinkDayDao().getAllDays().toMutableList()
        }
        //если дата другая, создать новый день
        val today = SimpleDateFormat("dd.MM").format(Date())
        if (today != drinkDays.last().date) { //если даты разные
            drinkDays.add(DrinkDay())
        }
        waterProgress = drinkDays.last().dayResult
    }

    private fun updateDB() {
        coroutineIO.launch {
            if (database.drinkDayDao().getAllDays().size < drinkDays.size) { //если добавился новый день
                database.drinkDayDao().insert(drinkDays.last())
            } else { //если нужно обновить старый
                database.drinkDayDao().update(drinkDays.last())
            }
        }
    }
}