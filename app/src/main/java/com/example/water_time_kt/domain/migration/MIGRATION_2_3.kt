package com.example.water_time_kt.domain.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MIGRATION_2_3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE drinkDays ADD COLUMN number INT default 0 NOT NULL")
    }
}