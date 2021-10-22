package com.nedashkivskyi.privatebankhelper.utils

sealed class NavigationDestination(val destination:String){
    object CurrencyRateScreen: NavigationDestination(Constants.CurrencyRate)
    object BanksLocationScreen: NavigationDestination(Constants.BanksLocation)
    object CashpointLocationScreen: NavigationDestination(Constants.CashpointLocation)
}
