package com.ponchikchik.criminalintent.data

import java.util.*

data class Crime(val id: UUID = UUID.randomUUID()) {
    var title: String? = null
    var date: Date = Date()
    var isSolved = false
}
