package com.ponchikchik.criminalintent

import androidx.fragment.app.Fragment

class MainActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment = CrimeFragment()
}
