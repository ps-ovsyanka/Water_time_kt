package com.example.water_time_kt.domain.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MIGRATION_1_2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE drinkDays ADD COLUMN description TEXT default '0' NOT NULL")
    }
}