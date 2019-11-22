package com.example.footballleague.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleague.R
import com.example.footballleague.data.network.responses.team_details_model.Squad
import com.example.footballleague.ui.view.home.TeamDetailsActivity
import kotlinx.android.synthetic.main.team_player_item_row.view.*


class TeamPlayerAdapter :
    RecyclerView.Adapter<TeamPlayerAdapter.TeamHolder>() {
    private lateinit var context: TeamDetailsActivity
    private var squad: List<Squad>? = null

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): TeamHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.team_player_item_row,
            parent, false
        )

        return TeamHolder(itemView)
    }


    override fun onBindViewHolder(holder: TeamHolder, postion: Int) {
        val squad = squad?.get(postion)
        squad?.let { holder.setData(it) }
    }


    fun setTeamList(squad: List<Squad>, context: TeamDetailsActivity) {
        this.squad = squad
        notifyDataSetChanged()
        this.context = context
    }

    override fun getItemCount() = squad?.size ?: 0

    inner class TeamHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(squad: Squad) {
            itemView.tvPlayer.text=squad.name
//            itemView.tvTeamName.text = team.name
//            itemView.tvClubColor.text = team.clubColors
//            itemView.tvUrl.text = team.website
//            itemView.tvVenue.text = team.venue
//            itemView.cvTeam.setOnClickListener {
//
//                val intent = Intent(context, TeamDetailsActivity::class.java)
//                intent.putExtra(idTeamDetails, team.id)
//                Log.i("teem", "id of team" + team.id)
//                context.startActivity(intent)
//            }
//
//            itemView.tvUrl.setOnClickListener {
//                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(team.website)))
//
//            }
        }


    }


}
