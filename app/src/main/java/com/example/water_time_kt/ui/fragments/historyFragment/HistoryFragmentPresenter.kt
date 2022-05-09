package com.example.water_time_kt.ui.fragments.historyFragment

import android.util.Log
import com.example.water_time_kt.domain.dao.DrinkDayDao
import kotlinx.coroutines.*

class HistoryFragmentPresenter(val drinkDayDao: DrinkDayDao) {

    private lateinit var view: IHistoryFragmentView

    fun onCreate(historyFragment: IHistoryFragmentView){
        view = historyFragment
        updateHistory()
        Log.e("ee", "historyFragment onCreate")
    }

    fun updateHistory(){
        val coroutineIO = CoroutineScope(Dispatchers.IO)
        coroutineIO.launch {
            Log.e("ee", "history update")
            view.updateHistory(drinkDayDao.getAllDays())
            cancel()
        }
    }

    fun onDestroy(){
        Log.e("ee", "historyFragment onDestroy")
    }
}