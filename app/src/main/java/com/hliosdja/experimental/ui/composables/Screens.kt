package com.hliosdja.experimental.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hliosdja.experimental.ui.viewmodels.DashboardEvent
import com.hliosdja.experimental.ui.viewmodels.DashboardViewModel
import com.hliosdja.experimental.ui.viewmodels.HomeViewModel
import com.hliosdja.experimental.ui.viewmodels.ProfileViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold {
        Box(Modifier.padding(it)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Navigate?")
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { viewModel.navigateBottomSheet("Dashboard Screen") }
                ) {
                    Text("Show options")
                }
                Button(
                    onClick = { viewModel.loadingBottomSheet() }
                ) {
                    Text("Show loading")
                }
            }
        }
    }
}

@Composable
fun Dashboard(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle().value

    Scaffold {
        Box(Modifier.padding(it)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(uiState.text)
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        viewModel.onEvent(DashboardEvent.BackButtonEvent)
                    }
                ) {
                    Text("Go back")
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Scaffold {
        Box(Modifier.padding(it)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Profile Screen")
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { viewModel.navigateBottomSheet() }
                ) {
                    Text("Show bottom sheet")
                }
            }
        }
    }
}

@Composable
fun SupportScreen() {
    Scaffold {
        CenteredText(text = "Support Screen", modifier = Modifier.padding(it))
    }
}