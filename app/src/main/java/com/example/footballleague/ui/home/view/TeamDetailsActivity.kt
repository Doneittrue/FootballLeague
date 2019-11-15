package com.example.footballleague.ui.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.footballleague.util.Constants

import com.example.footballleague.R
import com.example.footballleague.data.network.responses.team_details_model.TeamDetailsModels
import kotlinx.android.synthetic.main.activity_team_details.*

class TeamDetailsActivity : AppCompatActivity() {
    private var teamDetailsViewModel: TeamDetailsViewModel? = null
   private  var id:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)
         id = intent.getIntExtra(Constants.idTeamDetails,0)

        teamDetailsViewModel = ViewModelProvider(this).get(TeamDetailsViewModel::class.java)
        getDetailsTeam()
    }

    private fun getDetailsTeam() {
        teamDetailsViewModel?.getDetailsTeam(id)?.observe(this,
            Observer<TeamDetailsModels> {
                    teamDetails ->detailsModel(teamDetails)
            })
    }

    private fun detailsModel(teamDetails: TeamDetailsModels?) {

        tvTeamName.text= teamDetails!!.name
        tvAddress.text= teamDetails.address
        tvPhone.text= teamDetails.phone
        tvEmail.text= teamDetails.email
        tvClubColors.text= teamDetails.clubColors
        tvVenue.text= teamDetails.venue

    }

}
