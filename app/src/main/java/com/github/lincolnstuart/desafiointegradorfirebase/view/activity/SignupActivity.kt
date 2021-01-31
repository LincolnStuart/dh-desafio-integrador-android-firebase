package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.lincolnstuart.desafiointegradorfirebase.R
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivitySignupBinding
import com.github.lincolnstuart.desafiointegradorfirebase.model.auth.Signup
import com.github.lincolnstuart.desafiointegradorfirebase.util.extension.validateField
import com.github.lincolnstuart.desafiointegradorfirebase.viewmodel.SignupViewModel
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewmodel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
        //throw RuntimeException("Teste crashlytics")
    }

    private fun initComponents() {
        viewmodel = ViewModelProvider(this).get(SignupViewModel::class.java)
        viewmodel.logEventOnAnalytics(this.localClassName)
        setupObservers()
        configureSubmit()
    }

    private fun configureSubmit() {
        binding.btRegistrationSubmit.setOnClickListener {
            disableForm()
            if (validateForm()) {
                viewmodel.signup(getSignupObject())
            } else {
                enableForm()
            }
        }
    }

    private fun getSignupObject() = Signup(
        binding.tilRegistrationName.editText?.text.toString(),
        binding.tilRegistrationEmail.editText?.text.toString(),
        binding.tilRegistrationPassword.editText?.text.toString()
    )

    private fun setupObservers() {
        successObserver()
        failureObserver()
    }

    private fun successObserver() {
        viewmodel.authResultLiveData.observe(this) {
            goToGameListActivity()
        }
    }


    private fun failureObserver() {
        viewmodel.errorMessageLiveData.observe(this) {
            Toast.makeText(this@SignupActivity, it, Toast.LENGTH_SHORT).show()
            enableForm()
        }
    }

    private fun goToGameListActivity() {
        val intent = Intent(this@SignupActivity, GameListActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


    private fun validateForm(): Boolean {
        return validateName(binding.tilRegistrationName)
                && validateEmail(binding.tilRegistrationEmail)
                && validatePassword(binding.tilRegistrationPassword)
                && validatePasswordConfirmation(
            binding.tilRegistrationPasswordConfirmation,
            binding.tilRegistrationPassword.editText?.text.toString()
        )

    }

    private fun validateName(til: TextInputLayout): Boolean {
        return til.validateField(getString(R.string.invalid_name_minimum)) {
            viewmodel.validateName(it)
        }
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

    private fun validatePasswordConfirmation(til: TextInputLayout, password: String): Boolean {
        return til.validateField(getString(R.string.invalid_password_confirmation)) {
            viewmodel.validatePasswordConfirmation(password, it)
        }
    }


    private fun enableForm() {
        binding.apply {
            btRegistrationSubmit.isEnabled = true
            tilRegistrationName.isEnabled = true
            tilRegistrationEmail.isEnabled = true
            tilRegistrationPassword.isEnabled = true
            tilRegistrationPasswordConfirmation.isEnabled = true
            tvRegistrationLoader.visibility = View.GONE
        }
    }

    private fun disableForm() {
        binding.apply {
            btRegistrationSubmit.isEnabled = false
            tilRegistrationName.isEnabled = false
            tilRegistrationEmail.isEnabled = false
            tilRegistrationPassword.isEnabled = false
            tilRegistrationPasswordConfirmation.isEnabled = false
            tvRegistrationLoader.visibility = View.VISIBLE
        }
    }

}