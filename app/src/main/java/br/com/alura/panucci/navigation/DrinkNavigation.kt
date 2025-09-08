package br.com.alura.panucci.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.DrinksListScreen

fun NavGraphBuilder.drinkScreen(naviController: NavHostController) {
    composable(AppDestination.Drinks.route) {
        DrinksListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                naviController.navigate("${AppDestination.ProductDetails.route}/${product.id}")
            }
        )
    }
}
