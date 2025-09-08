package br.com.alura.panucci.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun PanucciNavHost(naviController: NavHostController) {
    NavHost(
        navController = naviController,
        startDestination = homeGraphRoute,
    ) {
        homeGraph(naviController)

        productDetail(naviController)

        checkoutScreen(naviController)

    }

}













