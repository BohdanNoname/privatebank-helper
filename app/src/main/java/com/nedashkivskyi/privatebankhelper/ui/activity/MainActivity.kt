package com.nedashkivskyi.privatebankhelper.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nedashkivskyi.privatebankhelper.R
import com.nedashkivskyi.privatebankhelper.ui.activity.components.BottomNavItem
import com.nedashkivskyi.privatebankhelper.ui.screen.banks_location.BanksLocationScreen
import com.nedashkivskyi.privatebankhelper.ui.screen.cashpoint_location.CashpointLocationScreen
import com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate.ExchangeRateScreen
import com.nedashkivskyi.privatebankhelper.utils.Constants
import com.nedashkivskyi.privatebankhelper.ui.theme.PrivateBankHelperTheme
import com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate.view_model.ExchangeRateViewModel
import com.nedashkivskyi.privatebankhelper.utils.NavigationDestination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val exchangeRateViewModel: ExchangeRateViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrivateBankHelperTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(items = listOf(
                            BottomNavItem(
                                name = "ATM",
                                route = Constants.CashpointLocation,
                                icon = painterResource(id = R.drawable.atm)
                            ),
                            BottomNavItem(
                                name = "Banks",
                                route = Constants.BanksLocation,
                                icon = painterResource(id = R.drawable.bank)
                            ),
                            BottomNavItem(
                                name = "Currency Rate",
                                route = Constants.CurrencyRate,
                                icon = painterResource(id = R.drawable.currency_rate)
                            )
                        ), navController = navController, onItemClick = {
                            navController.navigate(it.route)
                        })
                    }
                ) {
                    Navigation(navController = navController, exchangeRateViewModel = exchangeRateViewModel)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, exchangeRateViewModel: ExchangeRateViewModel){
    NavHost(navController = navController, startDestination = NavigationDestination.CurrencyRateScreen.destination){
        composable(NavigationDestination.CurrencyRateScreen.destination){
            ExchangeRateScreen(viewModel = exchangeRateViewModel)
        }

        composable(NavigationDestination.BanksLocationScreen.destination){
            BanksLocationScreen()
        }

        composable(NavigationDestination.CashpointLocationScreen.destination){
            CashpointLocationScreen()
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
){
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp)
    {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = item.route == navController.currentDestination?.route,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if(item.badgeCount > 0){
                            BadgeBox(
                                badgeContent = { Text(text = item.badgeCount.toString()) }
                            ) {
                                Text(text = item.name)
                            }
                        } else {
                            Icon(
                                painter = item.icon,
                                contentDescription = item.name,
                                modifier = Modifier.
                                        size(30.dp)
                            )
                        }
                        if (selected){
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                } )
        }
    }
}