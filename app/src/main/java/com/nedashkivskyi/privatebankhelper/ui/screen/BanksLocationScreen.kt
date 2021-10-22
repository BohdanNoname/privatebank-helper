package com.nedashkivskyi.privatebankhelper.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nedashkivskyi.privatebankhelper.utils.Constants

@Composable
fun BanksLocationScreen(){
    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.Center
    ){
        Text(text = Constants.BanksLocation)
    }
}