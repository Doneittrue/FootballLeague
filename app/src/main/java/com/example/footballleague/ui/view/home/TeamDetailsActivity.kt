package com.example.footballleague.ui.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleague.R
import com.example.footballleague.data.network.responses.team_details_model.Squad
import com.example.footballleague.data.network.responses.team_details_model.TeamDetailsModels
import com.example.footballleague.util.Constants
import kotlinx.android.synthetic.main.activity_team_details.*
import com.example.footballleague.ui.view.adapter.TeamPlayerAdapter as TeamPlayerAdapter

class TeamDetailsActivity : AppCompatActivity() {
    private var teamDetailsViewModel: TeamDetailsViewModel? = null
    private var id: Int = 0
    private var teamPlayerAdapter: TeamPlayerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        id = intent.getIntExtra(Constants.idTeamDetails, 0)

        // bind RecyclerView
        rv_team_player.layoutManager = LinearLayoutManager(this)
        rv_team_player.setHasFixedSize(true)

        teamDetailsViewModel = ViewModelProvider(this).get(TeamDetailsViewModel::class.java)
        teamPlayerAdapter = TeamPlayerAdapter()
        rv_team_player.adapter = teamPlayerAdapter

        getDetailsTeam()


    }

    private fun getDetailsTeam() {
        teamDetailsViewModel?.getDetailsTeam(id)?.observe(this, Observer<TeamDetailsModels> { teamDetails ->
            detailsModel(teamDetails)
        })
    }

    private fun detailsModel(teamDetails: TeamDetailsModels?) {
        teamPlayerAdapter?.setTeamList(teamDetails?.squad as ArrayList<Squad>, this@TeamDetailsActivity)
        rv_team_player.adapter = teamPlayerAdapter
        tvTeamName.text = teamDetails!!.name
        tvAddress.text = teamDetails.address
        tvPhone.text = teamDetails.phone
        tvEmail.text = teamDetails.email
        tvClubColors.text = teamDetails.clubColors
        tvVenue.text = teamDetails.venue

    }

}
