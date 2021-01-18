package com.example.crudapp.Database

import androidx.room.*

@Dao
interface MainanDao {
    @Insert
    suspend fun addMainan(mainan: Mainan)

    @Update
    suspend fun updateMainan(mainan: Mainan)

    @Delete
    suspend fun deleteMainan(mainan: Mainan)

    @Query("SELECT * FROM mainan")
    suspend fun getAllMainan(): List<Mainan>

    @Query("SELECT * FROM mainan WHERE id=:mainan_id")
    suspend fun getMainan(mainan_id: Int) : List<Mainan>

}