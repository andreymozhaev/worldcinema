package com.example.cinema.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cinema.R
import com.example.cinema.signup.SignupActivity

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
    }

    fun signup(view: View) {
        val intent=Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
}