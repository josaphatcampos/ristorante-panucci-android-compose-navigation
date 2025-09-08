package br.com.alura.panucci.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.alura.panucci.ui.components.BottomAppBarItem

@Composable
fun PanucciNavHost(naviController: NavHostController) {
    NavHost(
        navController = naviController,
        startDestination = highlightListRoute,
    ) {
        highlightsListScreen(naviController)

        menuScreen(naviController)

        drinkScreen(naviController)

        productDetail(naviController)

        checkoutScreen(naviController)

    }

}










