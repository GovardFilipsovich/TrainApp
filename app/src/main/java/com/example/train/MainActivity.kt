package com.example.train

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var login : EditText
    private lateinit var pass : EditText
    private var api = Retrofit.Builder().baseUrl("http://fspobot.tw1.ru:8080/auth/")
        .build().create(SUAI_API::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var head : ImageView
        var lock : ImageView
        var ball : ImageView

        head = findViewById(R.id.img_head)
        lock = findViewById(R.id.img_lock)
        ball = findViewById(R.id.img_ball)
        login = findViewById(R.id.lastname)
        pass = findViewById(R.id.pass1)

        Picasso.get().load("file:///android_asset/img1.png").fit().centerInside().into(head)
        Picasso.get().load("file:///android_asset/img2.png").fit().centerInside().into(lock)
        Picasso.get().load("file:///android_asset/SoccerBall-1.png").fit().centerCrop().into(ball)

    }

    fun Check(v : View){

        var JSON = JSONObject()
        JSON.put("email", login.text.toString())
        JSON.put("password", pass.text.toString())



        if (!(login.text.toString() == "" || pass.text.toString() == "")) {
            CoroutineScope(Dispatchers.Main).launch {
                var response =
                    api.login(JSON.toString().toRequestBody("application/json".toMediaTypeOrNull()))
                if (response.isSuccessful) {

                    if (JSONObject(response.body()?.string()).get("state") == "ok"){
                        Toast.makeText(this@MainActivity, "Успешно", Toast.LENGTH_LONG).show()
                        var i = Intent(this@MainActivity, Flags::class.java)
                        startActivity(i)
                    }else {
                        Log.i("H", "123")
                    }
                } else {
                    Log.i("H", "2")
                }

            }
        } else{
            Toast.makeText(this@MainActivity, "Заполните все пункты", Toast.LENGTH_LONG).show()
        }

    }

    fun toReg(v : View){
        var i = Intent(this, RegActivity::class.java)
        startActivity(i)
    }
}

