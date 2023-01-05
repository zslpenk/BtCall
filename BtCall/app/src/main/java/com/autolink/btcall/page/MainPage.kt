package com.autolink.btcall.page

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.autolink.btcall.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/*
 * Copyright (c) 2022, smuyyh@gmail.com All Rights Reserved.
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG            #
 * #                                                   #
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainPage(navController: NavController) {
    val pageState = rememberPagerState(
        initialPage = 0,
    )
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (topView, Page, button) = createRefs()
        //头顶的标题栏
        loadTopView(pageState,
            Modifier
                .fillMaxWidth()
                .width(70.dp)
                .constrainAs(topView) {
                    top.linkTo(parent.top)
                }
        )
        //中间的页面
        HorizontalPager(
            count = 2,
            state = pageState,
            modifier = Modifier
                .scrollable(
                    state = rememberPagerState(),
                    enabled = false,
                    orientation = Orientation.Horizontal
                )
                .constrainAs(Page) {
                    top.linkTo(topView.bottom)
                    bottom.linkTo(parent.bottom)
                }
                .padding(0.dp, 25.dp, 0.dp, 0.dp)
        ) { page ->
            when (page) {
                0 -> {
                    PhoneNumberPage(navController = navController)
                }
                1 -> {
                    ContractsPage(navController = navController)
                }
            }
        }
        //悬浮键盘
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun loadTopView(pageState: PagerState, modifier: Modifier) {
    val scope = rememberCoroutineScope()
    /* AndroidView(factory = {
        //加载AndroidView布局。
        LayoutInflater.from(it).inflate(R.layout.layout_mypage_top_view, null).apply {

        }
    }) {
        //更新UI数据

    }
*/
    var phoneView: View? = null
    var contractView: View? = null
    var tvPhone: TextView? = null
    var tvContract: TextView? = null

    AndroidView(factory = {
        //加载AndroidView布局。
        LayoutInflater.from(it).inflate(R.layout.layout_mypage_top_view, null).apply {
            phoneView = findViewById<View>(R.id.phone_view)
            contractView = findViewById<View>(R.id.contract_view)
            tvPhone = findViewById<TextView>(R.id.tv_phone)
            tvContract = findViewById<TextView>(R.id.tv_contract)
        }
    }, modifier = modifier) {
        //更新UI数据
        tvPhone?.setOnClickListener {
            scope.launch {
                pageState.scrollToPage(0)
            }
            phoneView?.visibility = View.VISIBLE
            contractView?.visibility = View.GONE
        }
        tvContract?.setOnClickListener {
            scope.launch {
                pageState.scrollToPage(1)
            }
            phoneView?.visibility = View.GONE
            contractView?.visibility = View.VISIBLE
        }
    }
}


@Composable
fun loadHoverButton(modifier: Modifier) {

}