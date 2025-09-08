package br.com.alura.panucci.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.HighlightsListScreen

internal const val highlightListRoute = "highlight"

fun NavGraphBuilder.highlightsListScreen(navController: NavController) {
    composable(highlightListRoute) {
        HighlightsListScreen(
            products = sampleProducts,
            onNavigateToCheckout = {
                navController.navigateToCheckout()
            },
            onNavigateToDetails = { product ->
                navController.navigateToProductDetail(product.id)
            }
        )
    }
}

fun NavController.navigateToHighlightsList(navOptions: NavOptions? = null) {
    navigate(highlightListRoute, navOptions)
}