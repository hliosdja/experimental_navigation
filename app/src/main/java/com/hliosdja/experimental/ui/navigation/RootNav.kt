package com.hliosdja.experimental.ui.navigation

import android.app.Activity
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.material.navigation.bottomSheet
import androidx.compose.material.navigation.rememberBottomSheetNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hliosdja.experimental.ui.composables.BottomSheetLayout
import com.hliosdja.experimental.ui.composables.BottomSheetParams
import com.hliosdja.experimental.ui.composables.Dashboard
import com.hliosdja.experimental.ui.composables.HomeScreen
import com.hliosdja.experimental.ui.composables.ProfileScreen
import com.hliosdja.experimental.ui.composables.SupportScreen
import com.hliosdja.experimental.ui.viewmodels.MainViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun RootNav(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val bottomSheetNavigator = rememberBottomSheetNavigator()

    navController.navigatorProvider.addNavigator(bottomSheetNavigator)

    NavigationEffects(
        navigationChannel = viewModel.navigationChannel,
        navHostController = navController
    )

    ModalBottomSheetLayout(modifier = modifier, bottomSheetNavigator = bottomSheetNavigator) {
        NavHost(
            navController = navController,
            startDestination = Destination.HomeScreen
        ) {
            composable(Destination.HomeScreen) {
                HomeScreen()
            }
            composable(Destination.Dashboard) {
                Dashboard()
            }
            composable(Destination.ProfileScreen) {
                ProfileScreen()
            }
            composable(Destination.SupportScreen) {
                SupportScreen()
            }
            bottomSheet(Destination.BottomSheet) {
                val params = navController.getPassedData<BottomSheetParams>(BOTTOM_SHEET_PARAMS)
                BottomSheetLayout(params)
            }
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
                is NavigationIntent.NavigateToWithData -> {
                    navHostController.apply {
                        currentBackStackEntry?.savedStateHandle?.set(
                            intent.key,
                            intent.data
                        )
                        navigate(intent.route) {
                            launchSingleTop = intent.isSingleTop
                            intent.popUpToRoute?.let { popUpToRoute ->
                                popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,
    route: String? = null,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.fullRoute,
        modifier = modifier,
        route = route,
        builder = builder
    )
}

fun NavGraphBuilder.bottomSheet(
    destination: Destination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable() (ColumnScope.(NavBackStackEntry) -> Unit)
) {
    bottomSheet(
        route = destination.fullRoute,
        arguments = arguments,
        deepLinks = deepLinks,
        content = content
    )
}

fun NavGraphBuilder.composable(
    destination: Destination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLink: List<NavDeepLink> = emptyList(),
    content: @Composable() (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) {
    composable(
        route = destination.fullRoute,
        arguments = arguments,
        deepLinks = deepLink,
        content = content
    )
}

fun <T> NavController.getPassedData(key: String): T? {
    return previousBackStackEntry?.savedStateHandle?.let { it[key] }
}



const val BOTTOM_SHEET_PARAMS = "bottomSheetParams"