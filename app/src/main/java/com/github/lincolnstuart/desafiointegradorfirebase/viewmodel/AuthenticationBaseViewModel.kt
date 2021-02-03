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

    fun validatePassword(value: String) =
        authenticationBusiness.validatePassword(value)

    fun validateEmail(value: String) =
        authenticationBusiness.validateEmail(value)

    fun validateName(value: String) =
        authenticationBusiness.validateName(value)

    fun validatePasswordConfirmation(value: String, passwordConfirmation: String) =
        authenticationBusiness.validateConfirmationPassword(value, passwordConfirmation)


}