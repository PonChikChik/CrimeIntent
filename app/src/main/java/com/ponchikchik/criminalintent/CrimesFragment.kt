package com.ponchikchik.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ponchikchik.criminalintent.data.CrimeLab
import java.util.*

class CrimesFragment : Fragment() {
    private val crimeList = CrimeLab.crimes

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
                    onClickCrimeListItem = { crimeId: UUID, view ->
                        val bundle = bundleOf("crimeId" to crimeId.toString())
                        view.findNavController().navigate(R.id.crimeFragment, bundle)
                    }
                )
            }
        }

        return view
    }
}
