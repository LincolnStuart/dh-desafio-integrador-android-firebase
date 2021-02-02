package com.github.lincolnstuart.desafiointegradorfirebase.model.auth

import android.util.Patterns
import com.github.lincolnstuart.desafiointegradorfirebase.util.Constants
import com.google.firebase.auth.AuthResult

class AuthenticationBusiness {

    private val repo: AuthenticationRepository by lazy {
        AuthenticationRepository()
    }

    fun login(
        login: Login,
        onSuccess: (AuthResult) -> Unit,
        onFailure: (String) -> Unit
    ) {
        repo.login(
            login,
            onSuccess,
            onFailure
        )
    }

    fun signup(
        signup: Signup,
        onSuccess: (AuthResult) -> Unit,
        onFailure: (String) -> Unit
    ) {
        repo.signup(
            signup,
            onSuccess,
            onFailure
        )
    }

    fun isAuthenticated(): Boolean = !repo.getUser().isNullOrBlank()

    fun validateEmail(email: String) =  !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun validatePassword(password: String) = password.count() < Constants.MIN_PASSWORD_CHARACTERS

    fun validateName(name: String) = name.count() < Constants.MIN_NAME_CHARACTERS

    fun validateConfirmationPassword(
        password: String,
        confirmationPassword: String
    ) = password != confirmationPassword


}