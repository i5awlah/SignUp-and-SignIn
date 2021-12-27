package com.example.signupandsignin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.signupandsignin.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val mobile = intent.getStringExtra("mobile")
        val location = intent.getStringExtra("location")

        binding.tvWelcome.text = "Welcome $name, \n\n Your Details are:\nMobile: $mobile \n Location: $location"

        binding.btnSignOut.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }
}