package com.autolink.btcall.page

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.autolink.btcall.ui.theme.DestCallingPage
import com.autolink.btcall.ui.theme.DestContractsPage

@Composable
fun ContractsPage(navController: NavController) {
    Column {
        Text(text = "Contracts Page", color = Color.Blue)
        Button(onClick = {
            navController.navigate(DestCallingPage) {
                popUpTo(DestContractsPage) {}}
        })
        {
            Text(text = "Go to Calling Page")
        }

    }
}