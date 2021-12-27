package com.example.signupandsignin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.signupandsignin.services.DatabaseHelper
import com.example.signupandsignin.models.User
import com.example.signupandsignin.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSubmit.setOnClickListener {
                val name = etName.text.toString()
                val mobile = etMobile.text.toString()
                val location = etLocation.text.toString()
                val password = etPassword.text.toString()

                etName.text.clear()
                etMobile.text.clear()
                etLocation.text.clear()
                etPassword.text.clear()

                if (name.isNotEmpty() && mobile.isNotEmpty() && location.isNotEmpty() && password.isNotEmpty()) {
                    val newUser = User(name, mobile, location, password)
                    if (databaseHelper.saveData(newUser) != -1) {
                        startActivity(
                            Intent(this@SignUpActivity, DetailsActivity::class.java)
                                .putExtra("name", name)
                                .putExtra("mobile", mobile)
                                .putExtra("location", location)
                        )
                        }
                } else {
                    Toast.makeText(this@SignUpActivity, "Please fill in all fields", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}