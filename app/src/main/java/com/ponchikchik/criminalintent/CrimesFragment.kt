package com.ponchikchik.criminalintent

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ponchikchik.criminalintent.data.Crime
import com.ponchikchik.criminalintent.data.CrimeLab
import com.ponchikchik.criminalintent.data.database.CrimeDB
import com.ponchikchik.criminalintent.data.database.CrimeDatabase
import java.util.*

class CrimesFragment : Fragment() {
    private val crimeList = CrimeLab.crimes
    private lateinit var crimesViewModel: CrimesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.crimes_fragment, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = CrimeDatabase.getInstance(application).crimeDao
        val viewModelFactory = CrimesViewModelFactory(dataSource, application)
        crimesViewModel = ViewModelProvider(this, viewModelFactory).get(CrimesViewModel::class.java)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = CrimeAdapter(
                    crimeList,
                    onClickCrimeListItem = { crimeId: UUID, _ ->
                        val bundle = bundleOf("crimeId" to crimeId.toString())
                        findNavController().navigate(R.id.crimeFragment, bundle)
                    }
                )
            }
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.new_crime -> {
                val crime = Crime()

                crimesViewModel.addCrime(
                    CrimeDB(
                        crime.id.toString(),
                        crime.title,
                        crime.date,
                        crime.isSolved,
                        crime.isRequiresPolice
                    )
                )
                CrimeLab.addCrime(crime)
                val bundle = bundleOf("crimeId" to crime.id.toString())
                findNavController().navigate(R.id.crimeFragment, bundle)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
