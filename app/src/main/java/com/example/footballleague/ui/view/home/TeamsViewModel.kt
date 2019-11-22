package com.example.footballleague.ui.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.footballleague.data.network.responses.teams_model.Team
import com.example.footballleague.data.repositories.TeamRepository

class TeamsViewModel : ViewModel() {

    private var teamRepository: TeamRepository =
        TeamRepository()

    fun getAllTeam(): LiveData<List<Team>> {
        return teamRepository.getMutableLiveData()  }
}