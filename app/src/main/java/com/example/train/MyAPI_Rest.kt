package com.example.train

import Country
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET

interface MyAPI_Rest {
    @GET ("all?fields=name,population,flags")
    fun getAll(): Call<ResponseBody>
}