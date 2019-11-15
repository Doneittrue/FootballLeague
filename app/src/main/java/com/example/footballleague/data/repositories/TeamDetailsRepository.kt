package com.example.footballleague.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.footballleague.util.Constants
import com.example.footballleague.data.network.RetrofitClient
import com.example.footballleague.data.network.responses.team_details_model.TeamDetailsModels
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamDetailsRepository {

    private val mutableLiveData = MutableLiveData<TeamDetailsModels>()

    fun getMutableLiveData(id:Int): MutableLiveData<TeamDetailsModels> {

        val userDataService = RetrofitClient.getInstance().api

        val call = userDataService.getTeamDetails(Constants.API_KEY, id)
        call.enqueue(object : Callback<TeamDetailsModels> {
            override fun onResponse(call: Call<TeamDetailsModels>, response: Response<TeamDetailsModels>) {
                val teamResponse = response.body()

                if (teamResponse != null) {

                    mutableLiveData.value = response.body()
                    Log.i("onResponsexxxx", "" + Gson().toJson(teamResponse))

                }

            }

            override fun onFailure(call: Call<TeamDetailsModels>, t: Throwable) {
                Log.i("asdsad", "onFailure")
            }
        })

        return mutableLiveData
    }
}