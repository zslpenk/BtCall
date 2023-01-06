package com.autolink.btcall.page

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.autolink.btcall.R
import com.autolink.btcall.ui.theme.DestContractsSearchPage
import com.autolink.btcall.ui.theme.DestMainPage
import kotlinx.coroutines.launch

@Composable
fun ContractsSearchPage(navController: NavController) {

    ConstraintLayout(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {

        addTopSearchView(modifier = Modifier.fillMaxSize(), navController = navController)

    }
}

@Composable
fun addTopSearchView(modifier: Modifier, navController: NavController) {

    var ivBack: View? = null
    AndroidView(factory = {
        //加载AndroidView布局。
        LayoutInflater.from(it).inflate(R.layout.top_search_view, null).apply {
            ivBack = findViewById(R.id.view_left)
        }
    }, modifier = modifier) {
        //更新UI数据
        ivBack?.setOnClickListener {
            navController.popBackStack()
        }
    }
}

