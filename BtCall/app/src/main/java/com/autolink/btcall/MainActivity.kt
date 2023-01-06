package com.autolink.btcall

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
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

            @Composable
            fun RequestPermission() {
                val context = LocalContext.current
                val lifeCycle = LocalLifecycleOwner.current.lifecycle


                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions(),
                    onResult = { isGranted ->
                        var result = true
                        isGranted.forEach { t, u ->
                            result = result && u
                        }

                        mainViewModel.permissionIsGranted = result
                    }
                )

                val lifecycleObserver = remember {
                    LifecycleEventObserver {_, event ->
                        if(event == Lifecycle.Event.ON_START) {
                            if (!permission[0].isGrantedPermission(context) ||
                                !permission[1].isGrantedPermission(context) ||
                                !permission[2].isGrantedPermission(context) ||
                                !permission[3].isGrantedPermission(context) ||
                                !permission[4].isGrantedPermission(context)) {

                                launcher.launch(permission)
                            }
                        }
                    }
                }

                DisposableEffect(key1 = lifeCycle, key2 = lifecycleObserver, effect = {
                    lifeCycle.addObserver(lifecycleObserver)

                    onDispose {
                        lifeCycle.removeObserver(lifecycleObserver)
                    }
                })
            }

            val checkAllPermission = permission[0].isGrantedPermission(LocalContext.current) &&
                    permission[1].isGrantedPermission(LocalContext.current) &&
                    permission[2].isGrantedPermission(LocalContext.current) &&
                    permission[3].isGrantedPermission(LocalContext.current) &&
                    permission[4].isGrantedPermission(LocalContext.current)

            if (checkAllPermission) {
                mainViewModel.permissionAction(true)
            }

            if (mainViewModel.permissionIsGranted) {
                BtCallTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Init(mainViewModel)
                    }
                }
            } else {
                RequestPermission()
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

private fun String.isGrantedPermission(context: Context): Boolean {
    return context.checkSelfPermission(this) == PackageManager.PERMISSION_GRANTED
}

val permission = arrayOf(Manifest.permission.READ_CONTACTS,
    Manifest.permission.READ_PHONE_STATE,
    Manifest.permission.READ_CALL_LOG,
    Manifest.permission.WRITE_CALL_LOG,
    Manifest.permission.CALL_PHONE
)