package com.example.water_time_kt.ui.fragments.historyFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.water_time_kt.Application
import com.example.water_time_kt.R
import com.example.water_time_kt.data.DrinkDay
import com.example.water_time_kt.ui.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject

class HistoryFragment : Fragment(), IHistoryFragmentView {

    init {
        Application.appComponent.inject(this)
    }

    @Inject
    lateinit var presenter: HistoryFragmentPresenter
    private val adapter: HistoryAdapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_history.adapter = adapter
        presenter.onCreate(this)
    }

    override fun updateHistory(list: List<DrinkDay>) {
        adapter.refresh(list)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

}