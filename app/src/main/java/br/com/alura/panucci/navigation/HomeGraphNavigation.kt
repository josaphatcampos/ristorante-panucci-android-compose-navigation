package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation
import br.com.alura.panucci.ui.components.BottomAppBarItem

internal const val homeGraphRoute = "home"
fun NavGraphBuilder.homeGraph(naviController: NavHostController) {
    navigation(startDestination = highlightListRoute, route = homeGraphRoute) {
        highlightsListScreen(naviController)
        menuScreen(naviController)
        drinkScreen(naviController)
    }
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(homeGraphRoute, navOptions)
}

fun NavController.navigateSingleTopWithPopUpTo(
    item: BottomAppBarItem
) {
    val (route, navigate) = when (item) {
        BottomAppBarItem.HighlightsList -> Pair(
            highlightListRoute,
            ::navigateToHighlightsList
        )

        BottomAppBarItem.Menu -> Pair(
            menuRoute,
            ::navigateToMenu
        )

        BottomAppBarItem.Drinks -> Pair(
            drinkRoute,
            ::navigateToDrink
        )
    }
    val navOptions = navOptions {
        launchSingleTop = true
        popUpTo(highlightListRoute)
    }

    navigate(navOptions)
}