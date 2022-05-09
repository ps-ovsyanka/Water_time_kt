package com.example.water_time_kt.ui.fragments.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.water_time_kt.Application
import com.example.water_time_kt.R
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment(): Fragment(), IMainFragmentView {

    private val presenter : MainFragmentPresenter =
        Application.injector.getIDependenciesPresenter().getMainFragmentPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate(this)

        tare_1.setOnClickListener {
            presenter.onClickTare1()
        }
        tare_2.setOnClickListener {
            presenter.onClickTare2()
        }
        tare_3.setOnClickListener {
            presenter.onClickTare3()
        }
        btn_cancel.setOnClickListener {
            presenter.cancelDrinkItem()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
    override fun setTextTarget(value: String?) {
        water_target.text = value
    }

    override fun setTextProgress(value: String?) {
        text_water_progress.text = value
    }

    override fun setProgress(progress: Int) {
        water_progress_bar.progress = progress
    }

    override fun setTextTare1(value: String?){
        tare_1.text = value
    }

    override fun setTextTare2(value: String?){
        tare_2.text = value
    }

    override fun setTextTare3(value: String?){
        tare_3.text = value
    }

}