package com.nedashkivskyi.privatebankhelper.ui.screen

import com.nedashkivskyi.privatebankhelper.common.Constants

sealed class NavigationDestination(val destination:String){
    object CurrencyRateScreen: NavigationDestination(Constants.CurrencyRate)
    object BanksLocationScreen: NavigationDestination(Constants.BanksLocation)
    object CashpointLocationScreen: NavigationDestination(Constants.CashpointLocation)
}
