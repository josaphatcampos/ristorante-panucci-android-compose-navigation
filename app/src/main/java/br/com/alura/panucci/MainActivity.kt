package br.com.alura.panucci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import br.com.alura.panucci.navigation.PanucciNavHost
import br.com.alura.panucci.navigation.drinkRoute
import br.com.alura.panucci.navigation.highlightListRoute
import br.com.alura.panucci.navigation.menuRoute
import br.com.alura.panucci.navigation.navigateToCheckout
import br.com.alura.panucci.navigation.navigateToDrink
import br.com.alura.panucci.navigation.navigateToHighlightsList
import br.com.alura.panucci.navigation.navigateToMenu
import br.com.alura.panucci.ui.components.BottomAppBarItem
import br.com.alura.panucci.ui.components.PanucciBottomAppBar
import br.com.alura.panucci.ui.components.bottomAppBarItems
import br.com.alura.panucci.ui.theme.PanucciTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
            val currentDestination = currentBackStackEntryAsState?.destination

            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val currentRoute = currentDestination?.route
                    val selectedItem by remember(currentDestination) {
                        val item = when (currentRoute) {
                            highlightListRoute -> BottomAppBarItem.HighlightsList
                            menuRoute -> BottomAppBarItem.Menu
                            drinkRoute -> BottomAppBarItem.Drinks
                            else -> BottomAppBarItem.HighlightsList
                        }
                        mutableStateOf(item)
                    }

                    val showBars = when (currentRoute) {
                        highlightListRoute, menuRoute, drinkRoute -> true
                        else -> false
                    }

                    val isShowFab = when (currentRoute) {
                        menuRoute, drinkRoute -> true
                        else -> false
                    }

                    PanucciApp(
                        bottomAppBarItemSelected = selectedItem,
                        onBottomAppBarItemSelectedChange = { item ->

                            val (route, navigate) = when (item) {
                                BottomAppBarItem.HighlightsList -> Pair(
                                    highlightListRoute,
                                    navController::navigateToHighlightsList
                                )

                                BottomAppBarItem.Menu -> Pair(
                                    menuRoute,
                                    navController::navigateToMenu
                                )

                                BottomAppBarItem.Drinks -> Pair(
                                    drinkRoute,
                                    navController::navigateToDrink
                                )
                            }
                            val navOptions = navOptions {
                                launchSingleTop = true
                                popUpTo(highlightListRoute)
                            }

                            navigate(navOptions)
                        },
                        onFabClick = {
                            navController.navigateToCheckout()
                        },
                        isShowTopBar = showBars,
                        isShowBottomBar = showBars,
                        isShowFab = isShowFab
                    ) {
                        PanucciNavHost(navController)
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanucciApp(
    bottomAppBarItemSelected: BottomAppBarItem? = bottomAppBarItems.first(),
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    onFabClick: () -> Unit = {},
    isShowTopBar: Boolean = false,
    isShowBottomBar: Boolean = false,
    isShowFab: Boolean = false,
    content: @Composable (() -> Unit)
) {
    Scaffold(
        topBar = {
            if (isShowTopBar) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Ristorante Panucci")
                    },
                )
            }
        },
        bottomBar = {
            if (isShowBottomBar) {
                PanucciBottomAppBar(
                    item = bottomAppBarItemSelected,
                    items = bottomAppBarItems,
                    onItemChange = onBottomAppBarItemSelectedChange,
                )
            }
        },
        floatingActionButton = {
            if (isShowFab) {
                FloatingActionButton(
                    onClick = onFabClick
                ) {
                    Icon(
                        Icons.Filled.PointOfSale,
                        contentDescription = null
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun PanucciAppPreview() {
    PanucciTheme {
        Surface {
            PanucciApp {}
        }
    }
}