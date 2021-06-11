package com.boltic28.carassistant.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.boltic28.carassistant.databinding.ActivityMainBinding
import com.boltic28.carassistant.domain.sign.AppUser
import com.boltic28.carassistant.domain.sign.AuthContract
import com.google.firebase.FirebaseApp
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModel()

    // for google auth
    private val authChecker =
        registerForActivityResult(AuthContract()) { user: AppUser ->
            println("->> user is: $user")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
    }

//ProgressBar
    fun showProgressBar() {
        binding.progressBar.progressLayout.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.progressBar.progressLayout.visibility = View.INVISIBLE
    }
}