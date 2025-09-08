package br.com.alura.panucci.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.ProductDetailsScreen

fun NavGraphBuilder.productDetail(naviController: NavHostController) {
    composable("${AppDestination.ProductDetails.route}/{productId}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("productId")
        sampleProducts.find {
            it.id == id
        }?.let { product ->
            ProductDetailsScreen(
                product = product,
                onNavigateToCheckout = {
                    naviController.navigate(AppDestination.Checkout.route)
                }
            )
        } ?: LaunchedEffect(Unit) {
            naviController.navigateUp()
        }

    }
}
