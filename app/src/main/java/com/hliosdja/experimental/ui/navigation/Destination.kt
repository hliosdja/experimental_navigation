package com.hliosdja.experimental.ui.navigation

sealed class Destination(protected val route: String, vararg params: Any) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    data object HomeScreen : NoArgumentsDestination(HOME_SCREEN)

    data object ProfileScreen : NoArgumentsDestination(PROFILE_SCREEN)

    data object SupportScreen : NoArgumentsDestination(SUPPORT_SCREEN)

    data object Dashboard : Destination(DASHBOARD_SCREEN, TEXT_KEY) {
        const val TEXT_KEY = Destination.TEXT_KEY
        operator fun invoke(text: String): String = route.appendParams(
            TEXT_KEY to text
        )
    }

    data object BottomSheet : Destination(BOTTOM_SHEET, BOTTOM_SHEET_TYPE_KEY) {
        const val BOTTOM_SHEET_TYPE_KEY = Destination.BOTTOM_SHEET_TYPE_KEY
        operator fun invoke(type: String): String = route.appendParams(
            BOTTOM_SHEET_TYPE_KEY to type
        )
    }

    companion object {
        private const val HOME_SCREEN = "home"
        private const val PROFILE_SCREEN = "profile"
        private const val DASHBOARD_SCREEN = "dashboard"
        private const val SUPPORT_SCREEN = "support"
        private const val BOTTOM_SHEET = "bottom_sheet"
        private const val BOTTOM_SHEET_TYPE_KEY = "bottom_sheet_type"
        private const val TEXT_KEY = "text"
    }
}

fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}