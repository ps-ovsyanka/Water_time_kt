package com.example.water_time_kt.domain.dao

import android.arch.persistence.room.*
import com.example.water_time_kt.data.DrinkDay

@Dao
interface DrinkDayDao {
    @Insert
    suspend fun insert(note : DrinkDay)

    @Delete
    suspend fun delete(note: DrinkDay)

    @Update
    suspend fun update(note: DrinkDay)

    @Query("select * from DrinkDays where id = :id")
    suspend fun getById(id : Long) : DrinkDay?

    @Query("select * from DrinkDays")
    suspend fun getAllDays(id : Long) : List<DrinkDay>
}