package com.nedashkivskyi.privatebankhelper.ui.screen.exchange_rate_screen.details

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)
