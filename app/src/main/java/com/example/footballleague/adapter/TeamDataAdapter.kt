package com.example.footballleague.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleague.R
import com.example.footballleague.data.network.responses.teams_model.Team
import com.example.footballleague.ui.home.view.TeamDetailsActivity
import com.example.footballleague.ui.home.view.TeamsActivity
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


    override fun getItemCount(): Int {
        return 6

    }

    fun setTeamList(teams: ArrayList<Team>,context: TeamsActivity) {
        this.teams = teams
        notifyDataSetChanged()
        this.context=context;
    }

    inner class TeamHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(team: Team) {
            itemView.tvTeamName.text = team.name
            itemView.tvClubColor.text = team.clubColors
            itemView.tvUrl.text = team.website
            itemView.tvVenue.text = team.venue
//            itemView.inset.setOnClickListener {
////                val area = Area(team.area.id, team.area.name)
//                val team = Team(
//                    team.address,
////                    area ,
//                    team.clubColors,
//                    team.crestUrl,
//                    team.email,
//                    team.founded,
//                    team.id,
//                    team.lastUpdated,
//                    team.name,
//                    team.phone,
//                    team.shortName,
//                    team.tla,
//                    team.venue,
//                    team.website
//                )
//
//                teamViewModel.insert(team)
//                context.toast("save")
//
//            }

            itemView.cvTeam.setOnClickListener {
                val intent = Intent(context, TeamDetailsActivity::class.java)
                intent.putExtra(idTeamDetails, team.id)
                context.startActivity(intent)
            }

            itemView.tvUrl.setOnClickListener {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(team.website)))

            }
        }


    }


}
