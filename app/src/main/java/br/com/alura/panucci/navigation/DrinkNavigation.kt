package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.DrinksListScreen

internal const val drinkRoute = "drinks"
fun NavGraphBuilder.drinkScreen(navController: NavHostController) {
    composable(drinkRoute) {
        DrinksListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetail(product.id)
            }
        )
    }
}

fun NavController.navigateToDrink(navOptions: NavOptions? = null) {
    navigate(drinkRoute, navOptions)
}
