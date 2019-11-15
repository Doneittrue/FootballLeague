package com.example.footballleague.ui.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleague.R
import com.example.footballleague.adapter.TeamDataAdapter
import com.example.footballleague.data.network.responses.teams_model.Team
import kotlinx.android.synthetic.main.teams_activity.*

class TeamsActivity : AppCompatActivity() {
    private var teamsViewModel: TeamsViewModel? = null
    private var teamDataAdapter: TeamDataAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teams_activity)

        // bind RecyclerView
        rv_team.layoutManager = LinearLayoutManager(this)
        rv_team.setHasFixedSize(true)



        teamsViewModel = ViewModelProvider(this).get(TeamsViewModel::class.java)
        teamDataAdapter = TeamDataAdapter()

        rv_team.adapter = teamDataAdapter
        getAllTeam()
    }

    //
    private fun getAllTeam() {

        teamsViewModel?.getAllTeam()?.observe(this,
            Observer<List<Team>> { teams ->
                teamDataAdapter?.setTeamList(teams as ArrayList<Team>,this@TeamsActivity)
                rv_team.adapter = teamDataAdapter

            })
    }
}
