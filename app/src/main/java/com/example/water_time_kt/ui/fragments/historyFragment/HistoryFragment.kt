package com.example.water_time_kt.ui.fragments.historyFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.water_time_kt.Application
import com.example.water_time_kt.R
import com.example.water_time_kt.ui.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * A fragment representing a list of Items.
 */
class HistoryFragment : Fragment(), IHistoryFragmentView {

    lateinit var presenter: HistoryFragmentPresenter

    init {
        presenter = Application.injector.getIDependenciesPresenter().getHistoryFragmentPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_history, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate(this)
    }


    override fun setHistoryListAdapter(adapter: HistoryAdapter) {
        list_history.adapter = adapter
    }


}