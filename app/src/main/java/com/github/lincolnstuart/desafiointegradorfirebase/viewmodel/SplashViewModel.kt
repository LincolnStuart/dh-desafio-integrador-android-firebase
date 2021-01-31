package com.github.lincolnstuart.desafiointegradorfirebase.viewmodel

class SplashViewModel: AuthenticationBaseViewModel() {

    fun isAuthenticated() = authenticationBusiness.isAuthenticated()

}