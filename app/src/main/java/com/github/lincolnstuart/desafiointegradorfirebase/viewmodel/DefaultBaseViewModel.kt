package com.github.lincolnstuart.desafiointegradorfirebase.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.lincolnstuart.desafiointegradorfirebase.model.analytics.AnalyticsBusiness
import kotlinx.coroutines.launch

abstract class DefaultBaseViewModel : ViewModel() {

    private val analyticsBusiness: AnalyticsBusiness by lazy{
        AnalyticsBusiness()
    }

    fun logEventOnAnalytics(name: String, bundle: Bundle? = null){
        analyticsBusiness.logEvent(name, bundle)
    }

}