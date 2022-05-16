package com.example.water_time_kt.ui.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module(includes = [ContextModule::class])
class SharedPreferenceModule {

    @Provides
    fun providePreferences(context :Context) = context.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)

}
