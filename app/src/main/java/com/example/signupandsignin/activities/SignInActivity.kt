package com.example.signupandsignin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.signupandsignin.services.DatabaseHelper
import com.example.signupandsignin.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }
    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSignIn.setOnClickListener {
                val mobile = etMobile.text.toString()
                val password = etPassword.text.toString()
                val user = databaseHelper.readOneUser(mobile, password)
                if (user.name.isNotEmpty()) {
                    startActivity(
                        Intent(this@SignInActivity, DetailsActivity::class.java)
                            .putExtra("name", user.name)
                            .putExtra("mobile", user.location)
                            .putExtra("location", user.location)
                    )
                }
            }
        }
    }
}