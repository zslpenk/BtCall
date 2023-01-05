package com.autolink.btcall.page

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.autolink.btcall.ui.theme.DestCallingPage
import com.autolink.btcall.ui.theme.DestContractsPage
import com.autolink.btcall.ui.theme.DestMainPage

@Composable
fun MainPage(navController: NavController) {
    Column {
        Text(text = "Main Page", color = Color.Red)
        Button(onClick = {
            navController.navigate(DestContractsPage) {
                popUpTo(DestMainPage) {}}
        })
        {
            Text(text = "Go to Calling Page")
        }

        }

}