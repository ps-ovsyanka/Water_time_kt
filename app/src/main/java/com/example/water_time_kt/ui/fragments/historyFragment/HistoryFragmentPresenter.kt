package com.example.water_time_kt.ui.fragments.historyFragment

import android.util.Log
import com.example.water_time_kt.domain.dao.DrinkDayDao
import kotlinx.coroutines.*
import javax.inject.Inject

class HistoryFragmentPresenter @Inject constructor(private val drinkDayDao: DrinkDayDao) {

    private lateinit var view: IHistoryFragmentView
    val coroutine = CoroutineScope(Dispatchers.Main)

    fun onCreate(historyFragment: IHistoryFragmentView){
        view = historyFragment
        updateHistory()
    }

    fun updateHistory(){
        coroutine.launch {
            view.updateHistory(drinkDayDao.getAllDays())
            Log.e("cc","list view")
        }
    }

    fun onDestroy(){
        coroutine.cancel()
    }
}