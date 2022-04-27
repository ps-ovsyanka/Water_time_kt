package com.example.water_time_kt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.water_time_kt.R
import com.example.water_time_kt.ui.MainActivity.Companion.PREF_TARE
import com.example.water_time_kt.ui.MainActivity.Companion.PREF_TARGET_NAME
import com.example.water_time_kt.ui.MainActivity.Companion.PREF_TARGET_SIZE
import com.example.water_time_kt.ui.MainActivity.Companion.drinkDays
import com.example.water_time_kt.ui.MainActivity.Companion.pref
import com.example.water_time_kt.ui.MainActivity.Companion.waterProgress
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment (): Fragment() {

    private lateinit var drinkItems : MutableList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        btn_cancel.setOnClickListener {
            cancelDrinkItem()
        }
    }

    override fun onResume() {
        text_water_progress.text = waterProgress.toString()
        water_target.text = pref.getString(PREF_TARGET_NAME, PREF_TARGET_SIZE)
        water_progress_bar.progress = waterProgress
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
        text_water_progress.text = waterProgress.toString()
        water_progress_bar.progress = waterProgress
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