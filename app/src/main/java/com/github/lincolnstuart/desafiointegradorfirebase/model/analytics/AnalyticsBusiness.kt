package com.github.lincolnstuart.desafiointegradorfirebase.model.analytics

import android.os.Bundle

class AnalyticsBusiness {

    private val repo: AnalyticsRepository by lazy {
        AnalyticsRepository()
    }

    fun logEvent(name: String, bundle: Bundle?) =
        repo.logEvent(name, bundle)

}