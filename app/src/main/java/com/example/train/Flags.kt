package com.example.train

import Country
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
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
    lateinit var the_most_big_countries : ArrayList<Country>
    lateinit var random_countries : ArrayList<Country>
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

        // Инициализация объектов
        var list = ArrayList<Country>()
        random_countries = ArrayList<Country>()
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

                // Сортировка по
                list.sortByDescending {list -> list.population}
                the_most_big_countries = list.slice(0..4) as ArrayList<Country>

                // Рандомная выборка
                for(i in 1..5){
                    var ind = (0..100).random()
                    random_countries.add(list[ind])
                }

            }
        }
    }

    fun moveToDiagram(v: View){
        intent = Intent(this, diagrams::class.java)

        // Передача данных о странах с самой большой популяцией
        var a = ArrayList<String>()
        var b = ArrayList<Int>()
        for (i in 0..4){
            a.add(the_most_big_countries[i].name)
            b.add(the_most_big_countries[i].population)
        }
        intent.putStringArrayListExtra("names_of_big", a)
        intent.putExtra("population_of_big", b)

        a = ArrayList<String>()
        b = ArrayList<Int>()
        for (i in 0..4){
            a.add(random_countries[i].name)
            b.add(random_countries[i].population)
        }
        intent.putStringArrayListExtra("names_of_random", a)
        intent.putExtra("population_of_random", b)

        startActivity(intent)
    }
}