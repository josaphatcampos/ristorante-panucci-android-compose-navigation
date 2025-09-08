package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.sampledata.sampleProducts
import br.com.alura.panucci.ui.screens.DrinksListScreen
import br.com.alura.panucci.ui.viewmodels.DrinksListViewModel

internal const val drinkRoute = "drinks"
fun NavGraphBuilder.drinkScreen(navController: NavHostController) {
    composable(drinkRoute) {
        val viewModel = viewModel<DrinksListViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        DrinksListScreen(
            uiState = uiState,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetail(product.id)
            }
        )
    }
}

fun NavController.navigateToDrink(navOptions: NavOptions? = null) {
    navigate(drinkRoute, navOptions)
}
