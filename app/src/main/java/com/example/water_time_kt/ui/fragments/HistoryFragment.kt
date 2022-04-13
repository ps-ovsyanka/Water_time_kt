package com.example.water_time_kt.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.water_time_kt.R
import com.example.water_time_kt.ui.MainActivity
import com.example.water_time_kt.ui.adapter.HistoryAdapter

/**
 * A fragment representing a list of Items.
 */
class HistoryFragment : Fragment() {
    var listHistory: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_history, container, false)
        val adapter = HistoryAdapter() //создание адаптера для списка
        listHistory = root.findViewById(R.id.list_history) //инициализация списка
        listHistory.setAdapter(adapter) //присвоение адаптера списку
        return root
    }
}