package com.example.water_time_kt.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.water_time_kt.di.dependencies.DependenciesAppDatabase
import com.example.water_time_kt.di.dependencies.DependenciesDrinkDayDao
import com.example.water_time_kt.domain.AppDatabase
import com.example.water_time_kt.domain.dao.DrinkDayDao
import com.example.water_time_kt.di.dependencies.DependencySharedPreferences
import java.lang.Exception

class ImplInjector(builder : Builder): Injector {


    private val dependencySharedPreferences : DependencySharedPreferences =
        builder.getDependencySharePreferences() ?: throw InjectorException()

    private val dependenciesAppDatabase : DependenciesAppDatabase =
        builder.getDependenciesApiDatabase() ?: throw InjectorException()

    private val dependenciesDrinkDayDao : DependenciesDrinkDayDao =
        builder.getDependenciesDrinkDayDao() ?: throw InjectorException()


    override fun getSharedPreferences(): SharedPreferences =
        dependencySharedPreferences.sharedPreferences

    override fun getDependenciesApiDatabase(): RoomDatabase =
        dependenciesAppDatabase.database

    override fun getDependenciesDrinkDayDao(): DrinkDayDao =
        dependenciesDrinkDayDao.drinkDayDao

    class Builder{

        companion object{
            const val DB_NAME = "database"
        }

        private var dependencySharePreferences : DependencySharedPreferences? = null
        private var dependenciesAppDatabase: DependenciesAppDatabase? = null
        private var dependenciesDrinkDayDao: DependenciesDrinkDayDao? = null

        fun dependencySharedPreferences(context: Context) : Builder = this.apply {
            dependencySharePreferences = DependencySharedPreferences(
                PreferenceManager.getDefaultSharedPreferences(context)
            )
        }
        fun dependencySharedPreferences(sharedPreferences: SharedPreferences) : Builder = this.apply {
            dependencySharePreferences = DependencySharedPreferences(sharedPreferences)
        }

        fun dependenciesApiDatabase(database: AppDatabase): Builder = this.apply {
            dependenciesAppDatabase = DependenciesAppDatabase(database)
        }
        fun dependenciesApiDatabase(context: Context): Builder = this.apply {
            dependenciesAppDatabase = DependenciesAppDatabase(
                Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
            )
        }

        fun dependenciesDrinkDayDao(): Builder = this.apply {
            dependenciesDrinkDayDao = dependenciesAppDatabase?.database?.let {
                DependenciesDrinkDayDao(
                    it.drinkDayDao())
            }
        }

        fun getDependencySharePreferences() = dependencySharePreferences

        fun getDependenciesApiDatabase() = dependenciesAppDatabase

        fun getDependenciesDrinkDayDao() = dependenciesDrinkDayDao

        fun build() : ImplInjector{
            return ImplInjector(this)
        }
    }

    class InjectorException() : Exception("dependencies has null")
}