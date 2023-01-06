package com.autolink.btcall.page

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.autolink.btcall.R

@Composable
fun CallingPage(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                .fillMaxSize()
        ) {

            Image(
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp)
                    .width(20.dp)
                    .height(20.dp)
                    .clickable {
                        navController.popBackStack()
                    },
                painter = painterResource(id = R.drawable.calling_scale),
                contentDescription = null
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "168 1382 2888",
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 40.sp
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Calling",
                color = Color.DarkGray,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 20.dp, end = 20.dp)
            ) {
                val modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp)
                    .size(20.dp, 20.dp)
                    .weight(1f)

                val receivePhone = modifier.clickable {
                    Toast.makeText(navController.context, "接听电话", Toast.LENGTH_SHORT).show()
                }
                val cancelCalling = modifier.clickable {
                    Toast.makeText(navController.context, "取消电话", Toast.LENGTH_SHORT).show()
                }

                val mute = modifier.clickable {
                    Toast.makeText(navController.context, "电话静音", Toast.LENGTH_SHORT).show()
                }
                val telephoneKeyboard = modifier.clickable {
                    Toast.makeText(navController.context, "拨号键盘", Toast.LENGTH_SHORT).show()
                }
                val handPhone = modifier.clickable {
                    Toast.makeText(navController.context, "转为手持", Toast.LENGTH_SHORT).show()
                }

                ItemImage(drawableId = R.drawable.calling_green, modifier = receivePhone)
                ItemImage(drawableId = R.drawable.calling_cancel, modifier = cancelCalling)
                ItemImage(drawableId = R.drawable.calling_unmute, modifier = mute)
                ItemImage(
                    drawableId = R.drawable.calling_telephonekeypad,
                    modifier = telephoneKeyboard
                )
                ItemImage(drawableId = R.drawable.calling_phone_hand, modifier = handPhone)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                val modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp)
                    .weight(1f)

                ItemTitle(R.string.calling_receive, modifier)
                ItemTitle(R.string.calling_cancel, modifier)
                ItemTitle(R.string.calling_mute, modifier)
                ItemTitle(R.string.calling_telephone_keyboard, modifier)
                ItemTitle(R.string.calling_hand_phone, modifier)
            }
        }
    }
}

@Composable
fun ItemTitle(resId: Int, modifier: Modifier) {
    Text(
        text = stringResource(id = resId),
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )
}

@Composable
fun ItemImage(drawableId: Int, modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = drawableId),
        contentDescription = null
    )
}


@Composable
fun callInputView(modifier: Modifier, openCallInput: MutableState<Boolean>) {
    if (openCallInput.value) {
        var ivCallingClose: ImageView? = null
        AndroidView(factory = {
            //加载AndroidView布局。
            LayoutInflater.from(it).inflate(R.layout.calling_input_view, null).apply {
                ivCallingClose = findViewById(R.id.iv_calling_close)
            }
        }, modifier = modifier) {
            ivCallingClose?.setOnClickListener {
                openCallInput.value = false
            }
        }
    }
}
