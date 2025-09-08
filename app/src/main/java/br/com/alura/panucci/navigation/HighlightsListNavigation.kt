package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.HighlightsListScreen
import br.com.alura.panucci.ui.viewmodels.HighlightsListViewModel

internal const val highlightListRoute = "highlight"

fun NavGraphBuilder.highlightsListScreen(navController: NavController) {
    composable(highlightListRoute) {
        val viewModel = viewModel<HighlightsListViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        HighlightsListScreen(
            uiState = uiState,
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