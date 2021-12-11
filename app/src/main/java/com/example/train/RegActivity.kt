package com.example.train

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit

class RegActivity : AppCompatActivity() {
    lateinit var nameEdit : EditText
    lateinit var lastNameEdit : EditText
    lateinit var password1Edit : EditText
    lateinit var password2Edit : EditText
    lateinit var emailEdit : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        var head : ImageView
        var head2 : ImageView
        var lock : ImageView
        var lock2 : ImageView
        var env : ImageView
        var ball : ImageView

        head = findViewById(R.id.img_head)
        head2 = findViewById(R.id.img_head2)
        env = findViewById(R.id.img_env)
        lock = findViewById(R.id.img_lock)
        lock2 = findViewById(R.id.img_lock_2)
        ball = findViewById(R.id.img_ball)

        Picasso.get().load("file:///android_asset/img1.png").fit().centerInside().into(head)
        Picasso.get().load("file:///android_asset/img1.png").fit().centerInside().into(head2)
        Picasso.get().load("file:///android_asset/img2.png").fit().centerInside().into(lock)
        Picasso.get().load("file:///android_asset/img2.png").fit().centerInside().into(lock2)
        Picasso.get().load("file:///android_asset/img3.png").fit().centerInside().into(env)
        Picasso.get().load("file:///android_asset/SoccerBall-1.png").fit().centerCrop().into(ball)

        nameEdit = findViewById(R.id.name)
        lastNameEdit = findViewById(R.id.lastname)
        password1Edit = findViewById(R.id.pass1)
        password2Edit = findViewById(R.id.pass2)
        emailEdit = findViewById(R.id.editTextTextEmailAddress)
    }



    fun reg(v: View){
        var retrofit = Retrofit.Builder().baseUrl("http://fspobot.tw1.ru:8080/auth/").build()
        var api = retrofit.create(SUAI_API::class.java)
        var JSON = JSONObject()

        var name = nameEdit.text.toString()
        var lastname = lastNameEdit.text.toString()
        var pass = password1Edit.text.toString()
        var pass2 = password2Edit.text.toString()
        var email = emailEdit.text.toString()

        if (!(name == "" || lastname == "" || pass == "" || pass2 == "" || email == "")) {
            if (Regex("""^\S+@\S+\.\S+${'$'}""").matches(email) != null) {
                if (pass == pass2) {
                    JSON.put("firstName", name)
                    JSON.put("lastName", lastname)
                    JSON.put("email", email)
                    JSON.put("password", pass)

                    CoroutineScope(Dispatchers.Main).launch {
                        var requestBody : RequestBody = JSON.toString().toRequestBody("application/json".toMediaTypeOrNull())
                        val response = api.register(requestBody)
                        if (response.isSuccessful) {
                            if (JSONObject(response.body()?.string()).get("state") == "ok"){
                                Toast.makeText(this@RegActivity, "Успешно", Toast.LENGTH_LONG).show()
                                var i = Intent(this@RegActivity, Flags::class.java)
                                startActivity(i)
                            }else {
                                Log.i("H", "123")
                            }
                        } else {
                            Log.i("H", "2")
                        }
                    }
                } else {
                    Toast.makeText(this@RegActivity, "Пароли не совпадают", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this@RegActivity, "Неправильная почта", Toast.LENGTH_LONG).show()
            }
        } else{
            Toast.makeText(this@RegActivity, "Заполните все поля", Toast.LENGTH_LONG).show()
        }

    }
}

