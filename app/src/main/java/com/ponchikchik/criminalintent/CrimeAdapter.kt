package com.ponchikchik.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ponchikchik.criminalintent.data.Crime
import java.util.*

class CrimeAdapter(
    private val values: List<Crime>,
    private val onClickCrimeListItem: (id: UUID, view: View) -> Unit
) : RecyclerView.Adapter<CrimeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.crime_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.title.text = item.title
        holder.date.text = item.date.toLocaleString()
        holder.crimeClickView.setOnClickListener { onClickCrimeListItem.invoke(item.id, it) }
    }

    override fun getItemCount(): Int = values.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.item_title)
        val date: TextView = view.findViewById(R.id.item_date)
        val crimeClickView: View = view.findViewById(R.id.item_crime)
    }
}
