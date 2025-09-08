package br.com.alura.panucci.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.outlined.LocalBar
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppDestination(val route: String){
    object Highlight: AppDestination("highlight")
    object Menu: AppDestination("menu")
    object Drinks: AppDestination("drinks")
    object ProductDetails: AppDestination("productDetails")
    object Checkout: AppDestination("checkout")

}

class BottomAppBarItem(
    val label: String,
    val icon: ImageVector,
    val destination: AppDestination,
)

val bottomAppBarItems = listOf(
    BottomAppBarItem(
        label = "Destaques",
        icon = Icons.Filled.AutoAwesome,
        destination = AppDestination.Highlight,
    ),
    BottomAppBarItem(
        label = "Menu",
        icon = Icons.Filled.RestaurantMenu,
        destination = AppDestination.Menu,
    ),
    BottomAppBarItem(
        label = "Bebidas",
        icon = Icons.Outlined.LocalBar,
        destination = AppDestination.Drinks,
    ),
)