package com.example.footballleague.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.footballleague.util.Constants
import com.example.footballleague.data.network.RetrofitClient
import com.example.footballleague.data.network.responses.teams_model.Team
import com.example.footballleague.data.network.responses.teams_model.TeemsModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class TeamRepository {
    private var teams = ArrayList<Team>()
    private val mutableLiveData = MutableLiveData<List<Team>>()


    fun getMutableLiveData(): MutableLiveData<List<Team>> {

        val userDataService = RetrofitClient.getInstance().api

        val call = userDataService.getTeam(Constants.API_KEY)
        call.enqueue(object : Callback<TeemsModel> {
            override fun onResponse(call: Call<TeemsModel>, response: Response<TeemsModel>) {
                val teamResponse = response.body()

                if (teamResponse != null) {
                    teams = teamResponse.teams as ArrayList<Team>
                    mutableLiveData.value = teams
                    Log.i("onResponsexxxx",""+Gson().toJson(teamResponse))

                }

            }

            override fun onFailure(call: Call<TeemsModel>, t: Throwable) {
                Log.i("asdsad","onFailure")
            }
        })

        return mutableLiveData
    }
}