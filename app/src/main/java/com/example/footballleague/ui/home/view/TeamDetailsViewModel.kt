package com.example.footballleague.ui.home.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.footballleague.data.network.responses.team_details_model.TeamDetailsModels
import com.example.footballleague.data.repositories.TeamDetailsRepository

class TeamDetailsViewModel : ViewModel() {

    private var teamDetailsRepository: TeamDetailsRepository =
        TeamDetailsRepository()

    fun getDetailsTeam(id:Int): LiveData<TeamDetailsModels> {
        return teamDetailsRepository.getMutableLiveData(id)
    }
}