package com.hliosdja.experimental.ui.composables

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hliosdja.experimental.ui.viewmodels.BottomSheetViewModel

enum class BottomSheetType {
    NAVIGATION,
    LOADING,
    NESTED_BOTTOM_SHEET,
    ANOTHER_BOTTOM_SHIT
}

@Composable
fun BottomSheetLayout(
    params: BottomSheetParams?,
    viewModel: BottomSheetViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    when (state.type) {
        BottomSheetType.NAVIGATION -> BottomSheetContent(params)
        BottomSheetType.LOADING -> LoadingOverlay()
        BottomSheetType.NESTED_BOTTOM_SHEET -> NestedBottomSheet(params)
        BottomSheetType.ANOTHER_BOTTOM_SHIT -> AnotherNestedShit(params)
        null -> { Log.e("BottomSheetLayout", "type is null") }
    }
}