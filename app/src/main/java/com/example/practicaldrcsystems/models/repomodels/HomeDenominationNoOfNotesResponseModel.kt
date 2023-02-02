package com.example.practicaldrcsystems.models.repomodels

import com.example.practicaldrcsystems.models.NotesAmountModel

data class HomeDenominationNoOfNotesResponseModel (
    val success: Boolean,
    val message: String,
    var listOfNotes: ArrayList<NotesAmountModel> = ArrayList()
)