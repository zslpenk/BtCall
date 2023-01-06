package com.autolink.btcall.page

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.autolink.btcall.R
import com.autolink.btcall.ui.theme.H3
import com.autolink.btcall.ui.theme.H5
import com.autolink.btcall.ui.theme.H7

@Composable
fun PhoneNumberSyncPage(navController: NavController) {
    var openSyncPhoneNumberPageState = remember {
        mutableStateOf(true)
    }
    syncPhonePageView(
        modifier = Modifier.fillMaxSize(),
        openPage = openSyncPhoneNumberPageState,
    )

}

@Composable
fun syncPhonePageView(
    modifier: Modifier,
    openPage: MutableState<Boolean>,
) {
    if (openPage.value) {
        ConstraintLayout(modifier) {
            val (syncType, title, button) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.calling_green),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .constrainAs(syncType) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(0.dp, 50.dp, 0.dp, 0.dp)
            )
            Text(text = "最近通话未同步", modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(syncType.bottom, 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            Log.i("MainPage", "onTouch ")      // 触摸事件
                        }, onTap = {
                            Log.i("MainPage", "onClick ")      // 点击事件
                        }, onDoubleTap = {
                            Log.i("MainPage", "onDoubleClick ")// 双击事件
                        }, onLongPress = {
                            Log.i("MainPage", "onLongClick ")  // 长按事件
                        }
                    )
                })
            var openDialog = remember {
                mutableStateOf(false)
            }
            Button(onClick = {
                openDialog.value = true
            }, modifier = Modifier
                .constrainAs(button) {
                    top.linkTo(title.bottom, 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                Text(text = "立即同步")
            }
            syncButtonView(openDialog = openDialog)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun syncButtonView(
    openDialog: MutableState<Boolean>,
) {
    if (openDialog.value) {
        Dialog(
            onDismissRequest = {
                openDialog.value = false
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnClickOutside = true
            ),
        ) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp),
                shape = RoundedCornerShape(8),
                color = Color.Gray
            ) {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (title, horCenterLine, cancleButton, verCenterLineView, sureButton) = createRefs()
                    Text(
                        text = "是否立即同步", modifier = Modifier.constrainAs(title) {
                            top.linkTo(parent.top, 50.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)

                        }, fontSize = H3, color = Color.White
                    )
                    horcenterLine(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .constrainAs(horCenterLine) {
                                bottom.linkTo(parent.bottom, 80.dp)
                            }
                    )

                    vercenterLine(
                        Modifier
                            .width(1.dp)
                            .height(80.dp)
                            .constrainAs(verCenterLineView) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                    )

                    Text(text = "取消", modifier = Modifier
                        .constrainAs(cancleButton) {
                            top.linkTo(horCenterLine.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(verCenterLineView.start)
                            bottom.linkTo(parent.bottom)
                        }
                        .clickable(onClick = {
                            openDialog.value = false
                        }), fontSize = H3, color = Color.White
                    )

                    Text(text = "确定",
                        modifier = Modifier
                            .constrainAs(sureButton) {
                                top.linkTo(horCenterLine.bottom)
                                start.linkTo(verCenterLineView.end)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                            .clickable(onClick = {
                                openDialog.value = false
                            }),
                        fontSize = H3,
                        color = Color.White
                    )

                }
            }
        }
    }
}


//横线Line
@Composable
fun horcenterLine(modifier: Modifier) {
    Canvas(
        modifier = modifier
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            start = Offset(x = 0F, y = 0f),
            end = Offset(x = canvasWidth, y = 0F),
            color = Color.White,
            strokeWidth = 5F
        )
    }
}

//竖线Line
@Composable
fun vercenterLine(modifier: Modifier) {
    Canvas(
        modifier = modifier
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            start = Offset(x = 0F, y = 0f),
            end = Offset(x = 0F, y = canvasHeight),
            color = Color.White,
            strokeWidth = 5F
        )
    }
}







