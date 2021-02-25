package com.example.cinema.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.cinema.R
import com.example.cinema.api.ApiRequest
import com.example.cinema.api.BASE_URL
import com.example.cinema.databinding.ActivitySigninBinding
import com.example.cinema.signup.SignupActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

class SigninActivity : AppCompatActivity() {

    lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        binding.btnSignin.setOnClickListener { signin() }
        binding.btnSignup.setOnClickListener { signup() }
        setContentView(binding.root)
    }

    private fun signin(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.signin(email, password)
                Log.d("Main", "Response: ${response.body()}")
                Log.d("Main", "Code: ${response.code()}")
                Log.d("Main", "token: ${response.body()?.token.toString()}")
            }
            catch (e: Exception){
                Log.e("Main", "Error: ${e.message}")
            }
        }
    }

    fun signup() {
        val intent=Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
}