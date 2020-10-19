package com.ponchikchik.criminalintent.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CrimeDao {
    @Insert
    suspend fun insert(crime: CrimeDB)

    @Update
    suspend fun update(crime: CrimeDB)

    @Query("SELECT * FROM crimes WHERE id = :id")
    suspend fun get(id: String): CrimeDB?

    @Query("DELETE FROM crimes")
    suspend fun clear()

    @Query("SELECT * FROM crimes")
    fun getAllCrimes(): LiveData<List<CrimeDB>>
}
