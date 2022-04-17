package com.example.water_time_kt.ui.fragments

import android.os.Bundle
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.String


class MainFragment (): Fragment(), View.OnClickListener {

    val drinkDayDao : DrinkDayDao by lazy {
        (getActivity() as MainActivity).database.drinkDayDao()
    }
    lateinit var drinkItems : MutableList<Int>

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
        }

        drinkItems = MainActivity.drinkDays.last().drinkItems.toMutableList()
    }


    private fun updateProgress (value: Int){
        MainActivity.waterProgress +=  value//изменение основной переменной прогресса

        waterProgressText.setText(String.valueOf(MainActivity.waterProgress))
        waterProgressBar.progress = MainActivity.waterProgress
        MainActivity.drinkDays.last().dayResult = MainActivity.waterProgress
        //если цель достигнута то день выполнен
        MainActivity.drinkDays.last().completed = MainActivity.drinkDays.last().dayResult >= MainActivity.waterTarget
    }

    override fun onClick(view: View?) {
        val btn = view as Button

        when (btn.id) {
            R.id.btn_cancel -> {
                drinkItems.removeLast()
            }
            else -> {
                val volumValue = btn.text.toString().toInt() //извлечение значения кнопки
                updateProgress(volumValue) //обновление прогресса
                drinkItems.add(volumValue)
            }
        }

    }
}