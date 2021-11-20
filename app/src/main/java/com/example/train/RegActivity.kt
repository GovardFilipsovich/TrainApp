package com.example.train

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class RegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        var head : ImageView
        var lock : ImageView
        var lock2 : ImageView
        var env : ImageView
        var ball : ImageView

        head = findViewById(R.id.img_head)
        env = findViewById(R.id.img_env)
        lock = findViewById(R.id.img_lock)
        lock2 = findViewById(R.id.img_lock_2)
        ball = findViewById(R.id.img_ball)

        Picasso.get().load("file:///android_asset/img1.png").fit().centerInside().into(head)
        Picasso.get().load("file:///android_asset/img2.png").fit().centerInside().into(lock)
        Picasso.get().load("file:///android_asset/img2.png").fit().centerInside().into(lock2)
        Picasso.get().load("file:///android_asset/img3.png").fit().centerInside().into(env)
        Picasso.get().load("file:///android_asset/SoccerBall-1.png").fit().centerCrop().into(ball)
    }
}

