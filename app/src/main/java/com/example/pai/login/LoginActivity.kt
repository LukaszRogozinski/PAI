package com.example.pai.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.pai.R
import com.example.pai.databinding.ActivityLoginBinding
import com.example.pai.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.lifecycleOwner = this

        binding.button.setOnClickListener {
            run {
                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

}
