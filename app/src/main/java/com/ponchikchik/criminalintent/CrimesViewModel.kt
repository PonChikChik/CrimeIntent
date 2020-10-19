package com.ponchikchik.criminalintent

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ponchikchik.criminalintent.data.Crime
import com.ponchikchik.criminalintent.data.database.CrimeDB
import com.ponchikchik.criminalintent.data.database.CrimeDao
import kotlinx.coroutines.launch
import java.util.*

class CrimesViewModel(
    private val database: CrimeDao,
    application: Application
) : AndroidViewModel(application) {
    var crimesList = listOf<Crime>()

    private fun initializeCrimeList() {
        viewModelScope.launch {
            getCrimeListFromDatabase().observeForever {
                crimesList = it.map { crimeFromDatabase ->
                    crimeFromDatabase.toCrime()
                }
            }
        }
    }

    fun getCrimeFromDatabase(id: UUID): LiveData<CrimeDB?> {
        return database.get(id.toString())
    }

    fun getCrimeListFromDatabase(): LiveData<List<CrimeDB>> {
        return database.getAllCrimes()
    }

    fun addCrime(crimeDB: CrimeDB) {
        viewModelScope.launch {
            database.insert(crimeDB)
        }
    }

    init {
        initializeCrimeList()
    }
}
