package com.example.train

import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SUAI_API {
    @POST("register")
    suspend fun register(@Body request: RequestBody): Response<ResponseBody>

    @POST("login")
    suspend fun login(@Body request: RequestBody): Response<ResponseBody>



}