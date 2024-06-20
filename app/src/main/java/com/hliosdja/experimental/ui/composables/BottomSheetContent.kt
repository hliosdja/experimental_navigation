package com.hliosdja.experimental.ui.composables

import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize

@Composable
fun BottomSheetContent(params: BottomSheetParams?) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text("Are you sure you want to navigate?")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally)
        ) {
            if (params?.negListener != null) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { params.negListener.invoke() }
                ) {
                    Text("Profile")
                }
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = params?.posListener!!
            ) {
                Text("Dashboard")
            }
        }
    }
}

@Parcelize
data class BottomSheetParams(
    val negListener: (() -> Unit)? = null,
    val posListener: () -> Unit
): Parcelable