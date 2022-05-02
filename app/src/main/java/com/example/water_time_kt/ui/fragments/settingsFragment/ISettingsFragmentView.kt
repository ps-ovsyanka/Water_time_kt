package com.example.water_time_kt.ui.fragments.settingsFragment

interface ISettingsFragmentView {
    fun setEditTextTarget(value: String?)
    fun setEditTextTare1(value: String?)
    fun setEditTextTare2(value: String?)
    fun setEditTextTare3(value: String?)

    fun getTextTarget(): String
    fun getTextTare1(): String
    fun getTextTare2(): String
    fun getTextTare3(): String
}