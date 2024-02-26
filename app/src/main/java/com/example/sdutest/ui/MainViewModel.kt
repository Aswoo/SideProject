package com.example.sdutest.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdutest.core.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: Application,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    val isDarkTheme = settingsRepository.getIsDarkTheme()

    init {
//        sendWidgetUpdateCommand(application)
    }

    fun updateIsDarkTheme(isDarkTheme: Boolean) = viewModelScope.launch {
        settingsRepository.updateIsDarkTheme(isDarkTheme)
    }
}
