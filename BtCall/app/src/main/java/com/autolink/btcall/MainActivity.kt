package com.autolink.btcall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.autolink.btcall.data.DataRepository
import com.autolink.btcall.page.*
import com.autolink.btcall.ui.theme.*
import com.autolink.btcall.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel by lazy { MainViewModel(DataRepository()) } 

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BtCallTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Init(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BtCallTheme {
        Greeting("Android")
    }
}

@Composable
fun Init(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val main = mainViewModel
    
    NavHost(navController = navController, startDestination = DestMainPage) {
        composable(DestMainPage) { MainPage(navController = navController) }
        composable(DestPhoneNumberPage) { PhoneNumberPage(navController = navController) }
        composable(DestPhoneNumberSyncPage) { PhoneNumberSyncPage(navController = navController) }
        composable(DestCallingPage) { CallingPage(navController = navController) }
        composable(DestContractsPage) { ContractsPage(navController = navController) }
        composable(DestContractsSyncPage) { ContractsSyncPage(navController = navController) }
    }
}