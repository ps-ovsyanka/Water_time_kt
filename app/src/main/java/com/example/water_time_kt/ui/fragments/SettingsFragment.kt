package com.example.water_time_kt.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.water_time_kt.Application
import com.example.water_time_kt.R
import com.example.water_time_kt.ui.MainActivity
import com.example.water_time_kt.ui.MainActivity.Companion.PREF_TARE
import com.example.water_time_kt.ui.MainActivity.Companion.PREF_TARGET_NAME
import com.example.water_time_kt.ui.MainActivity.Companion.PREF_TARGET_SIZE
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
    companion object{
        val pref: SharedPreferences = Application.injector.getDependenciesSharedPreferences()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextTarget.setText(pref.getString(PREF_TARGET_NAME, PREF_TARGET_SIZE))
        editTextTare1.setText(pref.getString(PREF_TARE[0].first, PREF_TARE[0].second))
        editTextTare2.setText(pref.getString(PREF_TARE[1].first, PREF_TARE[1].second))
        editTextTare3.setText(pref.getString(PREF_TARE[2].first, PREF_TARE[2].second))
    }

    override fun onPause() {
        pref.edit().apply {
            putString(PREF_TARGET_NAME, editTextTarget.text.toString()).apply()
            putString(PREF_TARE[0].first, editTextTare1.text.toString()).apply()
            putString(PREF_TARE[1].first, editTextTare2.text.toString()).apply()
            putString(PREF_TARE[2].first, editTextTare3.text.toString()).apply()
        }
        super.onPause()
    }

}