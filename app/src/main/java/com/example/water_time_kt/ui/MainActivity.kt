package com.example.water_time_kt.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.water_time_kt.Application
import com.example.water_time_kt.R
import com.example.water_time_kt.ui.fragments.historyFragment.HistoryFragment
import com.example.water_time_kt.ui.fragments.mainFragment.MainFragment
import com.example.water_time_kt.ui.fragments.settingsFragment.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), IMainActivityView {

    init {
        Application.appComponent.inject(this)
    }

    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_fragment_btn.setOnClickListener { presenter.toMainFragment() }
        history_fragment_btn.setOnClickListener { presenter.toHistoryFragment() }
        settings_fragment_btn.setOnClickListener { presenter.toSettingsFragment() }

        supportActionBar!!.hide()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        presenter.onCreate(this)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun toMainFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
    }

    override fun toHistoryFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, HistoryFragment()).commit()
    }

    override fun toSettingsFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, SettingsFragment()).commit()
    }
}