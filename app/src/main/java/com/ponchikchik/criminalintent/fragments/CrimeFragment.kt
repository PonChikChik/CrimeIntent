package com.ponchikchik.criminalintent.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ponchikchik.criminalintent.viewModels.CrimesViewModel
import com.ponchikchik.criminalintent.viewModels.CrimesViewModelFactory
import com.ponchikchik.criminalintent.MainActivity
import com.ponchikchik.criminalintent.R
import com.ponchikchik.criminalintent.data.Crime
import com.ponchikchik.criminalintent.data.database.CrimeDatabase
import com.ponchikchik.criminalintent.utils.ConfirmationListener
import com.ponchikchik.criminalintent.utils.DatePickerFragment
import kotlinx.android.synthetic.main.crime_fragment.*
import java.text.DateFormat
import java.text.DateFormat.MEDIUM
import java.util.*
import java.util.Locale.ENGLISH


class CrimeFragment : Fragment() {
    lateinit var crime: Crime
    private var crimeId: UUID? = null
    private val dateFormat = DateFormat.getDateInstance(MEDIUM, ENGLISH)
    private lateinit var crimesViewModel: CrimesViewModel

    private val listener = object : ConfirmationListener {
        override fun confirmButtonClicked(date: Date) {
            crime.date = date
            crime_date.text = dateFormat.format(date)
        }

        override fun cancelButtonClicked() {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)

        val application = requireNotNull(this.activity).application
        val dataSource = CrimeDatabase.getInstance(application).crimeDao
        val viewModelFactory = CrimesViewModelFactory(dataSource, application)
        crimesViewModel = ViewModelProvider(this, viewModelFactory).get(CrimesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = activity as? MainActivity
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.crime_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        crime_title.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        crime_solved.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
        }

        crime_date.setOnClickListener {
            val manager = childFragmentManager
            DatePickerFragment(crime.date, listener).show(manager, DIALOG_DATE)
        }
    }

    override fun onResume() {
        super.onResume()


        crimeId?.let { crimesViewModel.getCrimeFromDatabase(it).observe(this) { crimeDB ->
            crimeDB?.let {
                crime = crimeDB.toCrime()

                crime_title.append(crime.title)
                crime_date.text = dateFormat.format(crime.date)
                crime_solved.isChecked = crime.isSolved
            }
        } }
    }

    override fun onPause() {
        super.onPause()

        val activity = activity as? MainActivity
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(false)

        crimesViewModel.updateCrime(crime.toCrimeDB())
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        args?.let {
            crimeId = UUID.fromString(it.getString("crimeId"))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack(R.id.crimesFragment, true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    companion object {
        private const val DIALOG_DATE: String = "DialogDate"
    }
}
