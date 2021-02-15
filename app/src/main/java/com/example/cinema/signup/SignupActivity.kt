package com.example.cinema.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.cinema.R
import com.example.cinema.api.ApiRequest
import com.example.cinema.api.BASE_URL
import com.example.cinema.databinding.ActivitySignupBinding
import com.example.cinema.signin.SigninActivity
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        binding.signupButton.setOnClickListener { signup() }
        binding.signinButton.setOnClickListener { signin() }

        setContentView(binding.root)
    }

    private fun signup(){
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()
        val firstName = binding.editFirstName.text.toString()
        val lastName = binding.editLastName.text.toString()
        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .build()
                .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.signup(email, password, firstName, lastName)
                Log.e("Main", "Response: ${response.body()}")
            }
            catch (e: Exception){
                Log.e("Main", "Error: ${e.message}")
            }
        }
    }

    private fun signin(){
        val intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)
    }
}