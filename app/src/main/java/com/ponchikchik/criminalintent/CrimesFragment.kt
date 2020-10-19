package com.ponchikchik.criminalintent

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ponchikchik.criminalintent.data.Crime
import com.ponchikchik.criminalintent.data.database.CrimeDatabase
import kotlinx.android.synthetic.main.crimes_fragment.*
import java.util.*

class CrimesFragment : Fragment() {
    private lateinit var crimesViewModel: CrimesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application
        val dataSource = CrimeDatabase.getInstance(application).crimeDao
        val viewModelFactory = CrimesViewModelFactory(dataSource, application)
        crimesViewModel = ViewModelProvider(this, viewModelFactory).get(CrimesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.crimes_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()

        crimesViewModel.getCrimeListFromDatabase().observe(this) {
            val testCrimeList = it.map { crimeFromDatabase ->
                crimeFromDatabase.toCrime()
            }

            list.layoutManager = LinearLayoutManager(context)
            list.adapter = CrimeAdapter(
                testCrimeList,
                onClickCrimeListItem = { crimeId: UUID, _ ->
                    val bundle = bundleOf("crimeId" to crimeId.toString())
                    findNavController().navigate(R.id.crimeFragment, bundle)
                }
            )

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.new_crime -> {
                val crime = Crime()
                val bundle = bundleOf("crimeId" to crime.id.toString())
//                crimesViewModel.getCrimeListFromDatabase().

                crimesViewModel.addCrime(crime.toCrimeDB())
                findNavController().navigate(R.id.crimeFragment, bundle)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
