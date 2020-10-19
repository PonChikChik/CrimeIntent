package com.ponchikchik.criminalintent.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ponchikchik.criminalintent.utils.Converters


@Database(entities = [CrimeDB::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CrimeDatabase : RoomDatabase() {
    abstract val crimeDao: CrimeDao


    companion object {
        @Volatile
        private var INSTANCE: CrimeDatabase? = null

        fun getInstance(context: Context): CrimeDatabase {
            synchronized(this) {
                var instate = INSTANCE

                if (instate == null) {
                    instate = Room.databaseBuilder(
                        context.applicationContext,
                        CrimeDatabase::class.java,
                        "crimes"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instate
                }

                return instate
            }
        }
    }
}
