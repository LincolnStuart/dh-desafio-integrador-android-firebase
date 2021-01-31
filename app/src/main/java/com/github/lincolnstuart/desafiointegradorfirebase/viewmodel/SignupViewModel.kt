package com.github.lincolnstuart.desafiointegradorfirebase.viewmodel

import com.github.lincolnstuart.desafiointegradorfirebase.model.auth.Signup
import com.google.firebase.auth.AuthResult

class SignupViewModel : AuthenticationBaseViewModel() {

    fun signup(signup: Signup) {
        authenticationBusiness.signup(signup,
            { authResult: AuthResult -> authResultLiveData.postValue(authResult) },
            { message: String -> errorMessageLiveData.postValue(message) })
    }

}