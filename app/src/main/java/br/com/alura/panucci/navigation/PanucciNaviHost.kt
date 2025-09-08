package br.com.alura.panucci.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun PanucciNaviHost(naviController: NavHostController) {
    NavHost(
        navController = naviController,
        startDestination = AppDestination.Highlight.route,
    ) {
        highlightsListScreen(naviController)

        menuScreen(naviController)

        drinkScreen(naviController)

        productDetail(naviController)

        checkoutScreen(naviController)

    }

}








