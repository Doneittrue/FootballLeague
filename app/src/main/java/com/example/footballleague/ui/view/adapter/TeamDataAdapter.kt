package com.example.footballleague.ui.view.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleague.R
import com.example.footballleague.data.network.responses.teams_model.Team
import com.example.footballleague.ui.view.home.TeamDetailsActivity
import com.example.footballleague.ui.view.home.TeamsActivity
import com.example.footballleague.util.Constants.idTeamDetails
import kotlinx.android.synthetic.main.team_item_row.view.*
import java.util.*


class TeamDataAdapter :
    RecyclerView.Adapter<TeamDataAdapter.TeamHolder>() {
    private lateinit var context: TeamsActivity
    private var teams: ArrayList<Team>? = null

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): TeamHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.team_item_row,
            parent, false
        )

        return TeamHolder(itemView)
    }


    override fun onBindViewHolder(holder: TeamHolder, postion: Int) {
        val team = teams?.get(postion)
        team?.let { holder.setData(it) }
    }


    fun setTeamList(teams: ArrayList<Team>, context: TeamsActivity) {
        this.teams = teams
        notifyDataSetChanged()
        this.context = context
    }

    override fun getItemCount() = teams?.size ?: 0

    inner class TeamHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(team: Team) {
            itemView.tvTeamName.text = team.name
            itemView.tvClubColor.text = team.clubColors
            itemView.tvUrl.text = team.website
            itemView.tvVenue.text = team.venue
            itemView.cvTeam.setOnClickListener {

                val intent = Intent(context, TeamDetailsActivity::class.java)
                intent.putExtra(idTeamDetails, team.id)
                Log.i("teem", "id of team" + team.id)
                context.startActivity(intent)
            }

            itemView.tvUrl.setOnClickListener {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(team.website)))

            }
        }


    }


}
