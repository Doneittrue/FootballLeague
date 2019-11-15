package com.example.footballleague.data.network

import com.example.footballleague.util.Constants
import com.example.footballleague.data.network.responses.team_details_model.TeamDetailsModels
import com.example.footballleague.data.network.responses.teams_model.TeemsModel
import retrofit2.Call
import com.example.footballleague.util.Constants.teams
import com.example.footballleague.util.Constants.teamsNumber
import retrofit2.http.*


interface Api {
    @Headers("Accept: application/json")
    @GET("competitions/${teamsNumber}/teams")
    fun getTeam(@Header(Constants.X_Auth_Token) key: String): Call<TeemsModel>

    @Headers("Accept: application/json")
    @GET("${teams}/{${Constants.idTeamDetails}}")
    fun getTeamDetails(@Header(Constants.X_Auth_Token) key: String,
                       @Path(Constants.idTeamDetails)id: Int): Call<TeamDetailsModels>

}