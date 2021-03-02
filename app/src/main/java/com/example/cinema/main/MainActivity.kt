package com.example.cinema.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.cinema.R
import com.example.cinema.api.ApiRequest
import com.example.cinema.api.BASE_URL
import com.example.cinema.api.IMAGE_URL
import com.example.cinema.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCover()
    }

    private fun getCover(){
        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCover()
                withContext(Dispatchers.Main) {
                    val backgroundImage = response.body()?.backgroundImage
                    val foregroundImage = response.body()?.foregroundImage
                    Glide.with(applicationContext).load(IMAGE_URL+backgroundImage).into(binding.background)
                    Glide.with(applicationContext).load(IMAGE_URL+foregroundImage).into(binding.foreground)
                }
            }
            catch (e: Exception){
                Log.e("Main", "Error: ${e.message}")
            }
        }
    }
}