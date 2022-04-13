package com.example.water_time_kt.ui.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.water_time_kt.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}