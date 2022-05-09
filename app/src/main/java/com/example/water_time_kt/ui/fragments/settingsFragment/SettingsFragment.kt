package com.example.water_time_kt.ui.fragments.settingsFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.water_time_kt.Application
import com.example.water_time_kt.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(), ISettingsFragmentView {

    private val presenter : SettingsFragmentPresenter =
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
        editTextTarget.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                presenter.setTargetSize(s.toString())
            }
        })

        editTextTare1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                presenter.setTareOneSize(s.toString())
            }
        })

        editTextTare2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                presenter.setTareTwoSize(s.toString())
            }
        })

        editTextTare3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                presenter.setTareThreeSize(s.toString())
            }
        })
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

}