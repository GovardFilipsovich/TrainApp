package com.example.train

import Country
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Flags : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flags)
        var recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        var api = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v2/")
            .build()
            .create(MyAPI_Rest::class.java)

        var list = ArrayList<Country>()
        var adapter = MyAdapter(list)
        recyclerView.adapter = adapter
        GlobalScope.launch(Dispatchers.Main) {
            var response = api.getAll().awaitResponse()
            if(response.isSuccessful){
                var data = JSONArray(response.body()?.string())
                for (i in 0 until data.length()){
                    var obj = data.getJSONObject(i)
                    list.add(Country(obj.getString("name"),
                        obj.getInt("population"),
                        obj.getJSONObject("flags").getString("png")))
                }
                recyclerView.adapter = MyAdapter(list)
            }
        }









    }
}