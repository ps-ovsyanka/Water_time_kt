package com.example.water_time_kt.ui.fragments.settingsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.water_time_kt.Application
import com.example.water_time_kt.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(), ISettingsFragmentView {

    private var presenter : SettingsFragmentPresenter =
        Application.injector.getIDependenciesPresenter().getSettingsFragmentPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate(this)
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun setEditTextTarget(value: String?) {
        editTextTarget.setText(value)
    }

    override fun setEditTextTare1(value: String?) {
        editTextTare1.setText(value)
    }

    override fun setEditTextTare2(value: String?) {
        editTextTare2.setText(value)
    }

    override fun setEditTextTare3(value: String?) {
        editTextTare3.setText(value)
    }

    override fun getTextTarget(): String = editTextTarget.text.toString()

    override fun getTextTare1(): String = editTextTare1.text.toString()

    override fun getTextTare2(): String = editTextTare2.text.toString()

    override fun getTextTare3(): String = editTextTare3.text.toString()

}