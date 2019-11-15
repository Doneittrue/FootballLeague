package com.example.footballleague.data.network.responses.teams_model


import com.google.gson.annotations.SerializedName

data class AreaX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)