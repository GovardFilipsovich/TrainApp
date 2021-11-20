package com.example.train

import Country
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

interface MyAPI_Rest {
    @GET ("all")
    fun getAll(): Call<JSONObject>
}