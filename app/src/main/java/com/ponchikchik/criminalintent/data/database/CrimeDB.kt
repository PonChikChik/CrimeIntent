package com.ponchikchik.criminalintent.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ponchikchik.criminalintent.data.Crime
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
) {
    fun toCrime(): Crime {
        val crime = Crime(UUID.fromString(id))
        crime.title = title
        crime.date = date
        crime.isSolved = isSolved
        crime.isRequiresPolice = isRequiresPolice

        return crime
    }
}
