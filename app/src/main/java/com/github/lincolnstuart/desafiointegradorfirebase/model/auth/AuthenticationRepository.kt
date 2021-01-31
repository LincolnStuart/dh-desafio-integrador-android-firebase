package com.github.lincolnstuart.desafiointegradorfirebase.model.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthenticationRepository {

    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    fun getUser() = auth.currentUser?.uid

    fun login(
        login: Login,
        onSuccess: (AuthResult) -> Unit,
        onFailure: (String) -> Unit
    ) {
        getUser()?.let {
            onFailure("$it :/")
        }.run {
            auth.signInWithEmailAndPassword(login.email, login.password)
                .addOnSuccessListener {
                    onSuccess(it)
                }
                .addOnFailureListener {
                    onFailure(it.localizedMessage?.toString() ?: ":/")
                }
        }
    }

    fun signup(
        signup: Signup,
        onSuccess: (AuthResult) -> Unit,
        onFailure: (String) -> Unit
    ) {
        getUser()?.let {
            onFailure("$it :/")
        }.run {
            auth.createUserWithEmailAndPassword(signup.email, signup.password)
                .addOnSuccessListener {
                    onSuccess(it)
                    //val changes = userProfileChangeRequest { displayName = name }
                    //it.user?.updateProfile(changes)
                }.addOnFailureListener {
                    onFailure(it.localizedMessage?.toString() ?: ":/")
                }
        }
    }

}