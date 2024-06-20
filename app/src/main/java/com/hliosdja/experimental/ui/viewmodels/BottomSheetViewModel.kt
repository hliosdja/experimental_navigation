package com.hliosdja.experimental.ui.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hliosdja.experimental.ui.composables.BottomSheetType
import com.hliosdja.experimental.ui.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BottomSheetViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(SheetState())
    val state = _state.asStateFlow()

    init {
        val type: String = checkNotNull(savedStateHandle[Destination.BottomSheet.BOTTOM_SHEET_TYPE_KEY])
        _state.update { it.copy(type = BottomSheetType.valueOf(type)) }
    }

    override fun onCleared() {
        Log.d(BottomSheetViewModel::class.java.simpleName, "onCleared: ${hashCode()}", )
    }
}

data class SheetState(val type: BottomSheetType? = null)