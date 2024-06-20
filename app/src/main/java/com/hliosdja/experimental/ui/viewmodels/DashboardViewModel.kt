package com.hliosdja.experimental.ui.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hliosdja.experimental.ui.navigation.AppNavigator
import com.hliosdja.experimental.ui.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val TAG = "DashboardViewModel"

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    init {
        val dashboardText: String = savedStateHandle[Destination.Dashboard.TEXT_KEY] ?: ""
        _state.update { it.copy(text = dashboardText) }
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.BackButtonEvent -> navigateBack()
        }
    }

    private fun navigateBack() { appNavigator.tryNavigateBack() }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}

data class DashboardState(
    val text: String = ""
)

sealed class DashboardEvent {
    data object BackButtonEvent : DashboardEvent()
}