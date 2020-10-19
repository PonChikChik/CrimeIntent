package com.ponchikchik.criminalintent

import android.database.Cursor
import android.database.CursorWrapper
import com.ponchikchik.criminalintent.data.Crime
import com.ponchikchik.criminalintent.data.database.CrimeDB
import java.util.*

//class CrimeCursorWrapper(cursor: Cursor) : CursorWrapper(cursor) {
//    fun getCrime(): Crime {
//        val uuid = getString(getColumnIndex(CrimeDB.COLUMN_UUID))
//        val title = getString(getColumnIndex(CrimeDB.COLUMN_TITLE))
//        val date = getString(getColumnIndex(CrimeDB.COLUMN_DATE))
//        val isSolved = getString(getColumnIndex(CrimeDB.COLUMN_SOLVED))
//
//        val crime = Crime(UUID.fromString(uuid))
//        crime.title = title
//        crime.date = Date(date)
//        crime.isSolved = isSolved.toBoolean()
//
//        return crime
//    }
//}
