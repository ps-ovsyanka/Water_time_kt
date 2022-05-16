package com.example.water_time_kt.ui.di.modules

import android.content.Context
import com.example.water_time_kt.Application
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context : Application){

    @Provides
    fun providesContext() : Context = context

}