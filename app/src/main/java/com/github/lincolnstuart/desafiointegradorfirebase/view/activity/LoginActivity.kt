package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.github.lincolnstuart.desafiointegradorfirebase.R
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivityLoginBinding
import com.github.lincolnstuart.desafiointegradorfirebase.model.auth.Login
import com.github.lincolnstuart.desafiointegradorfirebase.util.extension.validateField
import com.github.lincolnstuart.desafiointegradorfirebase.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout

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
        setupObservers()
        configureLinkToSignup()
        configureSubmit()
    }

    private fun configureSubmit() {
        binding.btLoginSubmit.setOnClickListener {
            disableForm()
            if (validateForm()) {
                viewmodel.login(getLoginObject())
            } else {
                enableForm()
            }
        }
    }

    private fun getLoginObject() = Login(
        binding.tilLoginEmail.editText?.text.toString(),
        binding.tilLoginPassword.editText?.text.toString()
    )

    private fun configureLinkToSignup() {
        binding.tvLoginCreateAccount.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun setupObservers() {
        successObserver()
        failureObserver()
    }

    private fun successObserver() {
        viewmodel.authResultLiveData.observe(this) {
            goToGameListActivity()
        }
    }

    private fun goToGameListActivity() {
        startActivity(Intent(this@LoginActivity, GameListActivity::class.java))
        finish()
    }

    private fun failureObserver() {
        viewmodel.errorMessageLiveData.observe(this) {
            Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
            enableForm()
        }
    }

    private fun enableForm() {
        binding.apply {
            btLoginSubmit.isEnabled = true
            tilLoginEmail.isEnabled = true
            tilLoginPassword.isEnabled = true
            cbLoginRemember.isEnabled = true
            lavLoginAnimation.visibility = View.VISIBLE
            pbLoginLoader.visibility = View.GONE
        }
    }

    private fun disableForm() {
        binding.apply {
            btLoginSubmit.isEnabled = false
            tilLoginEmail.isEnabled = false
            tilLoginPassword.isEnabled = false
            cbLoginRemember.isEnabled = false
            lavLoginAnimation.visibility = View.INVISIBLE
            pbLoginLoader.visibility = View.VISIBLE
        }
    }

    private fun validateForm(): Boolean {
        return validateEmail(binding.tilLoginEmail)
                && validatePassword(binding.tilLoginPassword)
    }

    private fun validatePassword(til: TextInputLayout): Boolean {
        return til.validateField(getString(R.string.invalid_password_minimum)) {
            viewmodel.validatePassword(it)
        }
    }

    private fun validateEmail(til: TextInputLayout): Boolean {
        return til.validateField(getString(R.string.invalid_email)) {
            viewmodel.validateEmail(it)
        }
    }

}