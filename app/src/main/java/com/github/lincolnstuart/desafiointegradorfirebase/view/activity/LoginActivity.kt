package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivityLoginBinding
import com.github.lincolnstuart.desafiointegradorfirebase.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewmodel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() {
        viewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewmodel.logEventOnAnalytics(this.localClassName)
        binding.tvLoginCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}