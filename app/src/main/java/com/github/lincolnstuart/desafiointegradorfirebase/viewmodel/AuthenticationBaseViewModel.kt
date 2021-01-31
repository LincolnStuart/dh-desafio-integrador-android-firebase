package com.github.lincolnstuart.desafiointegradorfirebase.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.lincolnstuart.desafiointegradorfirebase.model.auth.AuthenticationBusiness
import com.google.firebase.auth.AuthResult

abstract class AuthenticationBaseViewModel: DefaultBaseViewModel() {

    var authResultLiveData: MutableLiveData<AuthResult> = MutableLiveData()
    var errorMessageLiveData: MutableLiveData<String> = MutableLiveData()

    protected val authenticationBusiness: AuthenticationBusiness by lazy {
        AuthenticationBusiness()
    }

    fun validatePassword(message: String) =
        authenticationBusiness.validatePassword(message)

    fun validateEmail(message: String) =
        authenticationBusiness.validateEmail(message)

    fun validateName(message: String) =
        authenticationBusiness.validateName(message)

    fun validatePasswordConfirmation(message: String, passwordConfirmation: String) =
        authenticationBusiness.validateConfirmationPassword(message, passwordConfirmation)


}