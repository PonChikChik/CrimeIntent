package com.ponchikchik.criminalintent.data

import java.util.*


class CrimeLab {
    companion object {
        var crimes: MutableList<Crime> = mutableListOf()
        private var instance: CrimeLab? = null

        fun get(): CrimeLab? {
            if (instance == null) {
                instance = CrimeLab()
            }

            return instance
        }

        fun getCrime(crimeId: UUID): Crime =
            crimes.first { UUID.fromString(it.id.toString()) == crimeId }

        fun addCrime(crime: Crime) {
            crimes.add(crime)
        }
    }
}
