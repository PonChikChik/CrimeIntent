package com.ponchikchik.criminalintent.data

import java.util.*

data class Crime(val id: UUID = UUID.randomUUID()) {
    var title: String = ""
    var date: Date = Date()
    var isSolved = false
    var isRequiresPolice = false
}
