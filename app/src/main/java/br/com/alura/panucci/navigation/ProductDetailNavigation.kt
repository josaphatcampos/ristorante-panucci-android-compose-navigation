package br.com.alura.panucci.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.ProductDetailsScreen

private const val productDetailsRoute = "productDetails"
private const val productIdKey = "productId"
fun NavGraphBuilder.productDetail(navController: NavHostController) {
    composable("$productDetailsRoute/{$productIdKey}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString(productIdKey)
        sampleProducts.find {
            it.id == id
        }?.let { product ->
            ProductDetailsScreen(
                product = product,
                onNavigateToCheckout = {
                    navController.navigateToCheckout()
                }
            )
        } ?: LaunchedEffect(Unit) {
            navController.navigateUp()
        }

    }
}

fun NavController.navigateToProductDetail(productId: String, navOptions: NavOptions? = null) {
    navigate("$productDetailsRoute/$productId", navOptions)
}