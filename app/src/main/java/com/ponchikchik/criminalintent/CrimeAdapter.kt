package com.ponchikchik.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ponchikchik.criminalintent.data.Crime
import java.text.DateFormat
import java.util.*

class CrimeAdapter(
    private val values: List<Crime>,
    private val onClickCrimeListItem: (id: UUID, view: View) -> Unit
) : RecyclerView.Adapter<CrimeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            DEFAULT_CRIME -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.crime_item, parent, false)
            )
            REQUIRED_CRIME -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.required_crime_item, parent, false)
            )
            else -> throw Error("Uncorrected crime type $viewType")
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH)

        holder.title.text = item.title
        holder.date.text = dateFormat.format(item.date)
        holder.crimeClickView.setOnClickListener {
            onClickCrimeListItem.invoke(
                UUID.fromString(item.id.toString()),
                it
            )
        }
        holder.isSolvedImageView.visibility = if (item.isSolved) VISIBLE else GONE
    }

    override fun getItemCount(): Int = values.size

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            false -> DEFAULT_CRIME
            true -> REQUIRED_CRIME
        }


    private fun getItem(position: Int): Boolean = values[position].isRequiresPolice


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.item_title)
        val date: TextView = view.findViewById(R.id.item_date)
        val crimeClickView: View = view.findViewById(R.id.item_crime)
        val isSolvedImageView: ImageView = view.findViewById(R.id.crime_solved)
    }


    companion object {
        private const val DEFAULT_CRIME = 0
        private const val REQUIRED_CRIME = 1
    }
}
