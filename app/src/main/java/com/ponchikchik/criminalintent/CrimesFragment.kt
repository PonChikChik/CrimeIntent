package com.ponchikchik.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ponchikchik.criminalintent.data.Crime
import com.ponchikchik.criminalintent.data.CrimeLab
import java.util.*

class CrimesFragment : Fragment() {
    private val crimeList = CrimeLab.crimes

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
                CrimeLab.addCrime(crime)
                val bundle = bundleOf("crimeId" to crime.id.toString())
                findNavController().navigate(R.id.crimeFragment, bundle)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
