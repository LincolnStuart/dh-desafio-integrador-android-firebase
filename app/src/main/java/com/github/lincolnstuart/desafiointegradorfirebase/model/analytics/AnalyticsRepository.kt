package com.github.lincolnstuart.desafiointegradorfirebase.model.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class AnalyticsRepository {

    private val analytics: FirebaseAnalytics by lazy {
        Firebase.analytics
    }

    fun logEvent(name: String, bundle: Bundle?) =
        analytics.logEvent(name, bundle)

}