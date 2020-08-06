package com.ponchikchik.criminalintent.data

import java.util.*
import kotlin.collections.ArrayList


class CrimeLab {
    companion object {
        var crimes: MutableList<Crime> = defaultValues()
        private var instance: CrimeLab? = null

        fun get(): CrimeLab? {
            if (instance == null) {
                instance = CrimeLab()
            }

            return instance
        }

        private fun defaultValues(): MutableList<Crime> {
            val crimesDefault: MutableList<Crime> = ArrayList()

            for (i in 0..100) {
                val crime = Crime()
                crime.title = "Crime #${i}"
                crime.isSolved = i % 2 == 0
                crime.isRequiresPolice = i % 2 == 0
                crimesDefault.add(crime)
            }

            return crimesDefault
        }

        fun getCrime(crimeId: UUID): Crime = crimes.first { it.id == crimeId }
    }
}
