package com.autolink.btcall.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.autolink.btcall.ui.theme.DestCallingPage
import com.autolink.btcall.ui.theme.DestContractsPage

@Composable
fun CallingPage(navController: NavController) {
    Column {
        Text(modifier = Modifier.padding(12.dp), text = "Calling Page", color = Color.Black)
        Button(onClick = {
            navController.navigate(DestContractsPage) {
                popUpTo(DestCallingPage) {}}
        })
        {
            Text(text = "Go to Contacts Page")
        }

    }
}