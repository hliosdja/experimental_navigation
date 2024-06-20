package com.hliosdja.experimental.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.hliosdja.experimental.ui.composables.BottomSheetParams
import com.hliosdja.experimental.ui.composables.BottomSheetType
import com.hliosdja.experimental.ui.navigation.AppNavigator
import com.hliosdja.experimental.ui.navigation.BOTTOM_SHEET_PARAMS
import com.hliosdja.experimental.ui.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel()  {

    fun navigateBottomSheet(dashboardText: String) {
        appNavigator.tryNavigateToWithData(
            route = Destination.BottomSheet(type = BottomSheetType.NAVIGATION.name),
            key = BOTTOM_SHEET_PARAMS,
            data = BottomSheetParams(
                negListener = { navigateToProfile() },
                posListener = { navigateToDashboard(dashboardText) }
            )
        )
    }

    fun loadingBottomSheet() {
        appNavigator.tryNavigateTo(Destination.BottomSheet(BottomSheetType.LOADING.name))
    }

    private fun navigateToDashboard(text: String) {
        appNavigator.tryNavigateTo(Destination.Dashboard(text))
    }

    private fun navigateToProfile() {
        appNavigator.tryNavigateTo(Destination.ProfileScreen())
    }
}