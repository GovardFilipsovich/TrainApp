package com.example.train

import Country
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Flags : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flags)
        var list = mutableListOf<Country>()
        var recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        var retrofit = Retrofit.Builder().baseUrl("https://restcountries.com/v2/").addConverterFactory(
            GsonConverterFactory.create()).build()
        var myapi : MyAPI_Rest = retrofit.create(MyAPI_Rest::class.java)
        var response = myapi.getAll().execute()
        if (response.isSuccessful){
            var json = JSONArray(response.body())
            if (json != null) {
                for (i in 0 until json.length()){
                    var country = JSONObject(json.get(i).toString())
                    list.add(Country(country.getString("name"), country.getInt("population").toString(), JSONObject(country.getString("flags")).getString("png")))
                }
            }
            recyclerView.adapter = MyAdapter(list)
        }





    }
}