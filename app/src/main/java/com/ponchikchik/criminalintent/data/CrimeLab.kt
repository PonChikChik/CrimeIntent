package com.ponchikchik.criminalintent.data

import android.content.Context
import java.util.*
import kotlin.collections.ArrayList


class CrimeLab(context: Context) {
    private val context: Context = context.applicationContext
    private var crimes: MutableList<Crime> = defaultValues()

    fun getCrime(crimeId: UUID): Crime = crimes.first { it.id == crimeId }

    companion object {
        private var instance: CrimeLab? = null

        operator fun get(context: Context): CrimeLab? {
            if (instance == null) {
                instance = CrimeLab(context)
            }

            return instance
        }

        fun defaultValues(): MutableList<Crime> {
            val crimesDefault: MutableList<Crime> = ArrayList()

            for (i in 0..100) {
                val crime = Crime()
                crime.title = "Crime #${i}"
                crime.isSolved = i % 2 == 0
                crimesDefault.add(crime)
            }

            return crimesDefault
        }
    }

}