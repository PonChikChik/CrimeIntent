package com.ponchikchik.criminalintent

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.ponchikchik.criminalintent.data.database.CrimeDB
import com.ponchikchik.criminalintent.data.database.CrimeDao
import kotlinx.coroutines.launch

class CrimesViewModel(
    val database: CrimeDao,
    application: Application
) : AndroidViewModel(application) {
    var crimesList = listOf<CrimeDB>()
    var crime: CrimeDB? = null

    private fun initializeCrimeList() {
        viewModelScope.launch {
            getCrimeListFromDatabase().observeForever {
                crimesList = it
            }
        }
    }

    private fun getCrimeListFromDatabase(): LiveData<List<CrimeDB>> {
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
