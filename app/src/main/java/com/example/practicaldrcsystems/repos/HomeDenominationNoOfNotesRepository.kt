package com.example.practicaldrcsystems.repos

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.practicaldrcsystems.models.NotesAmountModel
import com.example.practicaldrcsystems.models.repomodels.HomeDenominationNoOfNotesResponseModel
import com.example.practicaldrcsystems.utils.ExtFuncs.logE
import com.example.practicaldrcsystems.utils.ExtFuncs.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object HomeDenominationNoOfNotesRepository {
    private var responseGetter = MutableLiveData<HomeDenominationNoOfNotesResponseModel>()

    fun getNoOfNotesInMachine(
        /*db: DRCSystemsDB*/
    ): MutableLiveData<HomeDenominationNoOfNotesResponseModel> {
        responseGetter = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            fetchNoOfNotesInMachine(/*db*/)
        }

        return responseGetter
    }

    private suspend fun fetchNoOfNotesInMachine(
        /*db: DRCSystemsDB*/
    ) {
        val listOfNotes: ArrayList<NotesAmountModel> = ArrayList()
        try {
            listOfNotes.add(NotesAmountModel(denomination = 2000, noOfNotes = 40))
            listOfNotes.add(NotesAmountModel(denomination = 500, noOfNotes = 40))
            listOfNotes.add(NotesAmountModel(denomination = 100, noOfNotes = 100))
            listOfNotes.add(NotesAmountModel(denomination = 50, noOfNotes = 100))
            listOfNotes.add(NotesAmountModel(denomination = 20, noOfNotes = 125))
            listOfNotes.add(NotesAmountModel(denomination = 10, noOfNotes = 250))
            if (!listOfNotes.isNullOrEmpty()) {
                withContext(Dispatchers.Main) {
                    responseGetter.value = HomeDenominationNoOfNotesResponseModel(
                        success = true,
                        message = "Notes are available",
                        listOfNotes = listOfNotes
                    )
                }
            } else {
                withContext(Dispatchers.Main) {
                    responseGetter.value = HomeDenominationNoOfNotesResponseModel(
                        success = false,
                        message = "Machine is empty, Please try again"
                    )
                }
            }
        } catch (e: Exception) {
            logE("CheckAcceptInvitationResponseOutsideException", e.localizedMessage)
            withContext(Dispatchers.Main) {
                responseGetter.value = HomeDenominationNoOfNotesResponseModel(
                    success = false,
                    message = "${e.message}"
                )
            }
        }
    }
}