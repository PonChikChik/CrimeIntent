package com.ponchikchik.criminalintent.data

import com.ponchikchik.criminalintent.data.database.CrimeDB
import java.util.*

data class Crime(val id: UUID = UUID.randomUUID()) {
    var title: String = ""
    var date: Date = Date()
    var isSolved = false
    var isRequiresPolice = false

    fun toCrimeDB(): CrimeDB = CrimeDB(
        id = id.toString(),
        title = title,
        date = date,
        isSolved = isSolved,
        isRequiresPolice = isRequiresPolice
    )
}
