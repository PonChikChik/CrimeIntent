package com.ponchikchik.criminalintent.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ponchikchik.criminalintent.data.database.CrimeDao

class CrimesViewModelFactory(
    private val dataSource: CrimeDao,
    private val application: Application
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CrimesViewModel::class.java)) {
            return CrimesViewModel(dataSource, application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
