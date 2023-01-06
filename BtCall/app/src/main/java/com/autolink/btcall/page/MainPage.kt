package com.autolink.btcall.page

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.autolink.btcall.R
import com.autolink.btcall.ui.theme.DestContractsSearchPage
import com.autolink.btcall.ui.theme.DestMainPage
import com.autolink.btcall.ui.theme.H5
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/*
 *
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
    //开启拨打键盘的标识
    var openBeCallingInput = remember {
        mutableStateOf(false)
    }
    //打开最近通话同步页面
    var openSyncPhoneNumberPageState = remember {
        mutableStateOf(false)
    }

    //打开最近通话同步页面
    var openSyncingView = remember {
        mutableStateOf(false)
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (topView, button, Page, showCalling, syncPhonePage, syncView) = createRefs()
        //头顶的标题栏
        loadTopView(
            pageState,
            Modifier
                .fillMaxWidth()
                .width(70.dp)
                .constrainAs(topView) {
                    top.linkTo(parent.top)
                },
            navController
        )
        //中间的页面
        HorizontalPager(count = 2, state = pageState, modifier = Modifier
            .scrollable(
                state = rememberPagerState(),
                enabled = false,
                orientation = Orientation.Horizontal
            )
            .constrainAs(Page) {
                top.linkTo(topView.bottom, 25.dp)
                bottom.linkTo(parent.bottom)
            }) { page ->
            when (page) {
                0 -> {
                    PhoneNumberPage(navController = navController)
                }
                1 -> {
                    ContractsPage(navController = navController)
                }
            }
        }
        //键盘悬浮窗
        val floatBuModifier = Modifier
            .constrainAs(button) {
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
            .width(100.dp)
            .height(100.dp)
            .padding(20.dp, 0.dp, 0.dp, 20.dp)
            .clickable {
                openBeCallingInput.value = true
            }
        loadFloatBeCall(modifier = floatBuModifier)
        //拨打电话键盘页面
        val callModifier = Modifier
            .constrainAs(showCalling) {
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }
            .fillMaxWidth()
            .height(300.dp)
            .padding(20.dp, 0.dp, 20.dp, 20.dp)
        //拨打电话弹窗
        callInputView(callModifier, openBeCallingInput)
        //同步中弹窗
        syncView(
            Modifier
                .width(300.dp)
                .height(80.dp)
                .constrainAs(syncView) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, 20.dp)
                },
            openSyncingView
        )
        // 通话记录同步页面
        syncPhonePageView(
            Modifier
                .fillMaxSize()
                .constrainAs(syncPhonePage) {
                    top.linkTo(topView.bottom, 25.dp)
                    bottom.linkTo(parent.bottom)
                },
            openSyncPhoneNumberPageState
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun loadTopView(pageState: PagerState, modifier: Modifier, navController: NavController) {
    val scope = rememberCoroutineScope()
    var phoneView: View? = null
    var contractView: View? = null
    var tvPhone: TextView? = null
    var tvContract: TextView? = null
    var ivImage: ImageView? = null

    AndroidView(factory = {
        //加载AndroidView布局。
        LayoutInflater.from(it).inflate(R.layout.layout_mypage_top_view, null).apply {
            phoneView = findViewById<View>(R.id.phone_view)
            contractView = findViewById<View>(R.id.contract_view)
            tvPhone = findViewById<TextView>(R.id.tv_phone)
            tvContract = findViewById<TextView>(R.id.tv_contract)
            ivImage = findViewById<ImageView>(R.id.iv_search)
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
        ivImage?.setOnClickListener {
            navController.navigate(DestContractsSearchPage) {
                popUpTo(DestMainPage) {}
            }
        }
    }
}


//, onClick: () -> Unit
@Composable
fun loadFloatBeCall(
    modifier: Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.ic_becall),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun syncView(modifier: Modifier, syncing: MutableState<Boolean>) {
    if (syncing.value) {
        ConstraintLayout(modifier = modifier.background(Color.LightGray, RectangleShape)) {
            val (imageSync, title) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_sync),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(imageSync) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, 100.dp)
                    }
                    .width(20.dp)
                    .height(20.dp)
            )
            Text(
                text = "正在同步中",
                fontSize = H5,
                color = Color.White,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(imageSync.end, 50.dp)
                })
        }
    }
}

