package br.com.alura.panucci.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.ProductDetailsScreen
import br.com.alura.panucci.ui.viewmodels.ProductDetailsViewModel

private const val productDetailsRoute = "productDetails"
private const val productIdKey = "productId"
fun NavGraphBuilder.productDetail(navController: NavHostController) {
    composable("$productDetailsRoute/{$productIdKey}") { backStackEntry ->
        backStackEntry.arguments?.getString(productIdKey)?.let { id ->
            val viewModel = viewModel<ProductDetailsViewModel>()
            val uistate by viewModel.uiState.collectAsState()
            LaunchedEffect(Unit) {
                viewModel.findProductById(id)
            }
            ProductDetailsScreen(
                uistate = uistate,
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