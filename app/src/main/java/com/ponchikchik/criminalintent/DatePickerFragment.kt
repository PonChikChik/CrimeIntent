package com.ponchikchik.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.icu.util.Calendar.*
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalDate
import java.util.*

class DatePickerFragment(
    private val date: Date,
    private val listener: ConfirmationListener
) : DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = getInstance()
        calendar.time = date

        return DatePickerDialog(
            requireActivity(),
            this,
            calendar.get(YEAR),
            calendar.get(MONTH),
            calendar.get(DAY_OF_MONTH)
        )
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val date = GregorianCalendar(year, month, day).time
        listener.confirmButtonClicked(date)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        listener.cancelButtonClicked()
    }
}

interface ConfirmationListener {
    fun confirmButtonClicked(date: Date)
    fun cancelButtonClicked()
}
