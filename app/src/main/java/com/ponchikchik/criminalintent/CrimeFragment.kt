package com.ponchikchik.criminalintent

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.fragment.app.Fragment
import com.ponchikchik.criminalintent.data.Crime
import com.ponchikchik.criminalintent.data.CrimeLab
import kotlinx.android.synthetic.main.crime_fragment.*
import java.text.DateFormat
import java.util.*

class CrimeFragment : Fragment() {
    lateinit var crime: Crime
    private var crimeId: UUID? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = crimeId?.let { CrimeLab.getCrime(it) } ?: Crime()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.crime_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH)

        crime_title.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        crime_title.append(crime.title)
        crime_date.text = dateFormat.format(crime.date)
//        crime_date.isEnabled = false
        crime_solved.isChecked = crime.isSolved

        crime_solved.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        args?.let {
            crimeId = UUID.fromString(it.getString("crimeId"))
        }
    }
}
