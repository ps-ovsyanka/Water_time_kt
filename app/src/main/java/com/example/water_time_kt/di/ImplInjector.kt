package com.example.water_time_kt.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.water_time_kt.di.dependencies.DependenciesAppDatabase
import com.example.water_time_kt.di.dependencies.DependenciesDrinkDayDao
import com.example.water_time_kt.domain.AppDatabase
import com.example.water_time_kt.domain.dao.DrinkDayDao
import com.example.water_time_kt.di.dependencies.DependencySharedPreferences
import com.example.water_time_kt.di.dependencies.dependenciesPresenter.*
import java.lang.Exception

class ImplInjector(builder : Builder): Injector {

    private val dependencySharedPreferences : DependencySharedPreferences =
        builder.getDependencySharePreferences() ?: throw InjectorException()

    private val dependencyAppDatabase : DependenciesAppDatabase =
        builder.getDependencyApiDatabase() ?: throw InjectorException()

    private val dependencyDrinkDayDao : DependenciesDrinkDayDao =
        builder.getDependencyDrinkDayDao() ?: throw InjectorException()

    private val dependencyPresenter: DependenciesPresenter by lazy {
        DependenciesPresenter(
            DependenciesMainActivityPresenter(dependencyDrinkDayDao.drinkDayDao),
            DependenciesMainFragmentPresenter(dependencyDrinkDayDao.drinkDayDao),
            DependenciesSettingsFragmentPresenter(),
            DependenciesHistoryFragmentPresenter(dependencyDrinkDayDao.drinkDayDao)
        )
    }

    override fun getIDependenciesPresenter(): IDependenciesPresenter = dependencyPresenter

    override fun getDependenciesSharedPreferences(): SharedPreferences =
        dependencySharedPreferences.sharedPreferences

    override fun getDependenciesApiDatabase(): RoomDatabase =
        dependencyAppDatabase.database

    override fun getDependenciesDrinkDayDao(): DrinkDayDao =
        dependencyDrinkDayDao.drinkDayDao



    class Builder{

        companion object{
            const val PREF_FILE_NAME = "shared_pref"
            const val DB_NAME = "database"
        }
        private var dependencySharePreferences : DependencySharedPreferences? = null
        private var dependencyAppDatabase: DependenciesAppDatabase? = null

        private var dependencyDrinkDayDao: DependenciesDrinkDayDao? = null

        fun dependencySharedPreferences(context: Context) : Builder = this.apply {
            dependencySharePreferences = DependencySharedPreferences(
                context.getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE)
            )
        }

        fun dependencySharedPreferences(sharedPreferences: SharedPreferences) : Builder = this.apply {
            dependencySharePreferences = DependencySharedPreferences(sharedPreferences)
        }
        fun dependencyApiDatabase(database: AppDatabase): Builder = this.apply {
            dependencyAppDatabase = DependenciesAppDatabase(database)
        }

        fun dependencyApiDatabase(context: Context): Builder = this.apply {
            dependencyAppDatabase = DependenciesAppDatabase(
                Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
            )
        }

        fun dependencyDrinkDayDao(): Builder = this.apply {
            dependencyDrinkDayDao = dependencyAppDatabase?.database?.let {
                DependenciesDrinkDayDao(
                    it.drinkDayDao())
            }
        }

        fun getDependencySharePreferences() = dependencySharePreferences

        fun getDependencyApiDatabase() = dependencyAppDatabase

        fun getDependencyDrinkDayDao() = dependencyDrinkDayDao
        fun build() : ImplInjector{
            return ImplInjector(this)
        }
    }

    class InjectorException()  : Exception("dependencies has null")
}