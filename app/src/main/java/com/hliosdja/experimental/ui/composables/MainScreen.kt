package com.hliosdja.experimental.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.hliosdja.experimental.R
import com.hliosdja.experimental.ui.navigation.Destination
import com.hliosdja.experimental.ui.navigation.RootNav

@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = { /*TODO*/ })
            }
        }
    ) {
        RootNav(modifier = Modifier.padding(it))
    }
}

sealed class NavigationBarRoutes(
    val route: Destination,
    val icon: ImageVector
) {
    data object Home : NavigationBarRoutes(
        route = Destination.HomeScreen,
        icon = Icons.Default.Home
    )

    data object Profile : NavigationBarRoutes(
        route = Destination.ProfileScreen,
        icon = Icons.Default.Face
    )

    data object Support : NavigationBarRoutes(
        route = Destination.SupportScreen,
        icon = Icons.Default.Phone
    )
}