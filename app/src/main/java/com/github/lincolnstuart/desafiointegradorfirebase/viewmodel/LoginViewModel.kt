package com.github.lincolnstuart.desafiointegradorfirebase.viewmodel

import com.github.lincolnstuart.desafiointegradorfirebase.model.auth.Login
import com.google.firebase.auth.AuthResult

class LoginViewModel : AuthenticationBaseViewModel() {

    fun login(login: Login) {
        authenticationBusiness.login(login,
            { authResult: AuthResult -> authResultLiveData.postValue(authResult) },
            { message: String -> errorMessageLiveData.postValue(message) }
        )
    }

}