package com.ponchikchik.criminalintent.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "crimes")
data class CrimeDB(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "date")
    val date: Date = Date(),

    @ColumnInfo(name = "is_solved")
    val isSolved: Boolean = false,

    @ColumnInfo(name = "is_requires_police")
    val isRequiresPolice: Boolean = false
)
