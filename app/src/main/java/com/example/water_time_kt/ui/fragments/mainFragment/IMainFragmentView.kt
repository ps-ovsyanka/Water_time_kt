package com.example.water_time_kt.ui.fragments.mainFragment

interface IMainFragmentView {
    fun setTextTarget(value: String?)
    fun setTextProgress(value: String?)
    fun setProgress(progress: Int)
    fun setTextTare1(value: String?)
    fun setTextTare2(value: String?)
    fun setTextTare3(value: String?)
}