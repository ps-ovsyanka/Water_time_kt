package com.example.water_time_kt.ui.fragments.historyFragment

import com.example.water_time_kt.ui.MainActivityPresenter.Companion.drinkDays
import com.example.water_time_kt.ui.adapter.HistoryAdapter

class HistoryFragmentPresenter() {

    lateinit var view: IHistoryFragmentView

    fun onCreate(historyFragment: IHistoryFragmentView){
        view = historyFragment
        view.setHistoryListAdapter(HistoryAdapter(drinkDays))
    }
}