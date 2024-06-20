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
class ProfileViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun navigateBottomSheet() {
        appNavigator.tryNavigateToWithData(
            route = Destination.BottomSheet(type = BottomSheetType.NESTED_BOTTOM_SHEET.name),
            key = BOTTOM_SHEET_PARAMS,
            data = BottomSheetParams(
                posListener = {
                    navigateBack()
                    appNavigator.tryNavigateToWithData(
                        route = Destination.BottomSheet(type = BottomSheetType.ANOTHER_BOTTOM_SHIT.name),
                        key = BOTTOM_SHEET_PARAMS,
                        data = BottomSheetParams(
                            posListener = {
                                navigateBack()
                            }
                        )
                    )
                }
            )
        )
    }

    fun navigateBack() {
        appNavigator.tryNavigateBack()
    }
}