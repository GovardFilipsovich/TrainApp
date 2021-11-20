package com.example.train

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var login : EditText
    private lateinit var pass : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var head : ImageView
        var lock : ImageView
        var ball : ImageView

        head = findViewById(R.id.img_head)
        lock = findViewById(R.id.img_lock)
        ball = findViewById(R.id.img_ball)
        login = findViewById(R.id.login)
        pass = findViewById(R.id.pass1)

        Picasso.get().load("file:///android_asset/img1.png").fit().centerInside().into(head)
        Picasso.get().load("file:///android_asset/img2.png").fit().centerInside().into(lock)
        Picasso.get().load("file:///android_asset/SoccerBall-1.png").fit().centerCrop().into(ball)
    }

    fun Check(v : View){
        if ((login.text.toString() == "") or (pass.text.toString() == "")){
            Toast.makeText(this, "Вы не заполнили поле", Toast.LENGTH_SHORT).show()
        }
        else{
            var i = Intent(this, Flags::class.java)
            startActivity(i)
        }
    }

    fun toReg(v : View){
        var i = Intent(this, RegActivity::class.java)
        startActivity(i)
    }
}

