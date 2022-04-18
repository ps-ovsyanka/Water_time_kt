package com.example.water_time_kt.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.water_time_kt.R
import com.example.water_time_kt.domain.dao.DrinkDayDao
import com.example.water_time_kt.ui.MainActivity
import com.example.water_time_kt.ui.MainActivity.Companion.PREF_TARE
import com.example.water_time_kt.ui.MainActivity.Companion.PREF_TARGET_NAME
import com.example.water_time_kt.ui.MainActivity.Companion.PREF_TARGET_SIZE
import com.example.water_time_kt.ui.MainActivity.Companion.drinkDays
import com.example.water_time_kt.ui.MainActivity.Companion.pref
import com.example.water_time_kt.ui.MainActivity.Companion.waterProgress
import java.lang.String


class MainFragment (): Fragment() {

    private lateinit var drinkItems : MutableList<Int>
    private lateinit var waterProgressText : TextView
    private lateinit var waterTargetText : TextView
    private lateinit var waterProgressBar : ProgressBar
    private lateinit var tare_1 : Button
    private lateinit var tare_2 : Button
    private lateinit var tare_3 : Button
    private lateinit var btnCancel : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            waterProgressText = findViewById(R.id.text_water_progress)

            waterTargetText = findViewById(R.id.water_target)

            waterProgressBar = findViewById(R.id.water_progress_bar)

            tare_1 = findViewById(R.id.tare_1)
            tare_2 = findViewById(R.id.tare_2)
            tare_3 = findViewById(R.id.tare_3)
            btnCancel = findViewById(R.id.btn_cancel)
            tare_1.setOnClickListener {
                val volumValue = pref.getString(PREF_TARE[0].first, PREF_TARE[0].second)?.toInt()?:0
                updateProgress(volumValue)
                drinkItems.add(volumValue)
            }
            tare_2.setOnClickListener {
                val volumValue = pref.getString(PREF_TARE[1].first, PREF_TARE[1].second)?.toInt()?:0
                updateProgress(volumValue)
                drinkItems.add(volumValue)
            }
            tare_3.setOnClickListener {
                val volumValue = pref.getString(PREF_TARE[2].first, PREF_TARE[2].second)?.toInt()?:0
                updateProgress(volumValue)
                drinkItems.add(volumValue)
            }
            btnCancel.setOnClickListener {
                cancelDrinkItem()
            }
        }
    }

    override fun onResume() {

        Log.e("f djndasdfasfaf", "mainFragment onResume")
        waterProgressText.text = waterProgress.toString()
        waterTargetText.text = pref.getString(PREF_TARGET_NAME, PREF_TARGET_SIZE)
        waterProgressBar.progress = waterProgress
        drinkItems = drinkDays.lastOrNull()?.drinkItems?.toMutableList()?: mutableListOf()
        tare_1.text = pref.getString(PREF_TARE[0].first, PREF_TARE[0].second)
        tare_2.text = pref.getString(PREF_TARE[1].first, PREF_TARE[1].second)
        tare_3.text = pref.getString(PREF_TARE[2].first, PREF_TARE[2].second)
        super.onResume()
    }

    override fun onPause() {
        drinkDays.lastOrNull()?.drinkItems = drinkItems
        super.onPause()
    }

    private fun updateProgress (value: Int){
        waterProgress +=  value//изменение основной переменной прогресса
        waterProgressText.setText(String.valueOf(waterProgress))
        waterProgressBar.progress = waterProgress
        drinkDays.lastOrNull()?.dayResult = waterProgress
        //если цель достигнута то день выполнен
        drinkDays.lastOrNull()?.completed = drinkDays.last().dayResult >= pref.getString(PREF_TARGET_NAME, PREF_TARGET_SIZE)?.toInt()?:PREF_TARGET_SIZE.toInt()
    }

    private fun cancelDrinkItem(){
        val value = drinkItems.lastOrNull()?: 0
        updateProgress(-value)
        drinkItems.removeLastOrNull()
    }

}