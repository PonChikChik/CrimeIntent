package com.ponchikchik.criminalintent

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ponchikchik.criminalintent.data.database.CrimeDB.CrimeTable.COLUMN_DATE
import com.ponchikchik.criminalintent.data.database.CrimeDB.CrimeTable.COLUMN_SOLVED
import com.ponchikchik.criminalintent.data.database.CrimeDB.CrimeTable.COLUMN_TITLE
import com.ponchikchik.criminalintent.data.database.CrimeDB.CrimeTable.COLUMN_UUID
import com.ponchikchik.criminalintent.data.database.CrimeDB.CrimeTable.TABLE_NAME

class CrimeDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }


    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Crimes.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_UUID UUID PRIMARY KEY," +
                    "$COLUMN_TITLE," +
                    "$COLUMN_DATE," +
                    "$COLUMN_SOLVED)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}
