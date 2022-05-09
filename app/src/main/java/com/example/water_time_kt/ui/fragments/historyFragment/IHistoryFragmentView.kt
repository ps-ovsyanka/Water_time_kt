package com.example.water_time_kt.ui.fragments.historyFragment

import com.example.water_time_kt.data.DrinkDay

interface IHistoryFragmentView {
    fun updateHistory(list: List<DrinkDay>)
}