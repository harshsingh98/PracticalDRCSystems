package com.example.practicaldrcsystems.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaldrcsystems.models.repomodels.HomeDenominationNoOfNotesResponseModel
import com.example.practicaldrcsystems.repos.HomeDenominationNoOfNotesRepository

class MainViewModel : ViewModel() {
    private var homeNotesInATMLiveData: MutableLiveData<HomeDenominationNoOfNotesResponseModel>? = null

    fun getNotesInATMMachine(/*db: DRCSystemsDB*/):
            MutableLiveData<HomeDenominationNoOfNotesResponseModel>? {
        homeNotesInATMLiveData =
            HomeDenominationNoOfNotesRepository.getNoOfNotesInMachine(/*db*/)
        return homeNotesInATMLiveData
    }
}