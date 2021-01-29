package com.github.lincolnstuart.desafiointegradorfirebase.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.lincolnstuart.desafiointegradorfirebase.databinding.ActivitySignupBinding
import com.github.lincolnstuart.desafiointegradorfirebase.viewmodel.SignupViewModel

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
    }
}