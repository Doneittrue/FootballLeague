package com.example.footballleague.ui.view.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleague.R
import com.example.footballleague.data.network.responses.teams_model.Team
import com.example.footballleague.ui.view.adapter.TeamDataAdapter
import com.example.footballleague.util.EndlessRecyclerViewScrollListener
import com.example.footballleague.util.toast
import kotlinx.android.synthetic.main.teams_activity.*
import kotlin.math.ceil


class TeamsActivity : AppCompatActivity() {
    private lateinit var team1: java.util.ArrayList<Team>
    private lateinit var pages: List<List<Team>>
    private var PAGE = 1

    private var team = ArrayList<Team>()


    var linearLayoutManager = LinearLayoutManager(this)

    private var teamsViewModel: TeamsViewModel? = null
    private var teamDataAdapter: TeamDataAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teams_activity)
//        pages = getPages(team, 6)

        // bind RecyclerView
        rv_team.layoutManager = linearLayoutManager
        rv_team.setHasFixedSize(true)


        teamsViewModel = ViewModelProvider(this).get(TeamsViewModel::class.java)
        teamDataAdapter = TeamDataAdapter(team, this)


        rv_team.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {


                Log.i("asdasd",""+PAGE)
                if (PAGE<=pages.size&&pages.isNotEmpty()) {
                    ++PAGE
                    Fun(PAGE)


                }

            }
        })
        rv_team.adapter = teamDataAdapter
        getAllTeam()

    }

    //
    private fun getAllTeam() {
        if (PAGE == 1) {
            team.clear()
            rv_team.recycledViewPool.clear()
            teamDataAdapter?.notifyDataSetChanged()

        }

        teamsViewModel?.getAllTeam()?.observe(this,
            Observer<List<Team>> { teams ->


                team.addAll(teams)
                pages = getPages(team, 6)
                Log.i("asdsad",""+pages.size)

                Fun(PAGE)


            })

    }

    private fun Fun(page: Int) {

         team1 = ArrayList()
        rv_team.adapter = TeamDataAdapter(team1, this@TeamsActivity)

        team1.addAll(pages[page])


        teamDataAdapter?.notifyItemRangeChanged(teamDataAdapter?.itemCount!!, team1.size)

    }

    fun <T> getPages(c: Collection<T>?, pageSize: Int?): List<List<T>> {
        var pageSize = pageSize
        if (c == null)
            return emptyList()
        val list = java.util.ArrayList(c)
        if (pageSize == null || pageSize <= 0 || pageSize > list.size)
            pageSize = list.size
        val numPages = ceil(list.size.toDouble() / pageSize).toInt()
        val pages = java.util.ArrayList<List<T>>(numPages)
        var pageNum = 0
        while (pageNum < numPages)
            pages.add(list.subList(pageNum * pageSize, (++pageNum * pageSize).coerceAtMost(list.size)))
        return pages
    }


}
