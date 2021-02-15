package com.example.cinema.launch

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.cinema.R
import com.example.cinema.signin.SigninActivity
import com.example.cinema.signup.SignupActivity

class LaunchActivity : AppCompatActivity() {

    lateinit var sp:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        sp = getSharedPreferences("APP_SETTINGS", Context.MODE_PRIVATE)
        val handler = Handler()
        handler.postDelayed({start()}, 3000)
    }

    fun start(){
        val flag = sp.getBoolean("flag", false)
        if(!flag){
            val editor = sp.edit()
            editor.putBoolean("flag", true)
            editor.apply()
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }
    }
}