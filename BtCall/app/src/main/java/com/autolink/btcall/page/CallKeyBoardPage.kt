package com.autolink.btcall.page

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.autolink.btcall.R
import com.autolink.btcall.ui.theme.H3
import com.autolink.btcall.ui.theme.H4
import com.autolink.btcall.ui.theme.H6
import com.autolink.btcall.ui.theme.H7

@Composable
fun CallKeyBoardPage(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        color = Color.Gray,
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            val showCloseView = remember {
                mutableStateOf(true)
            }

            loadCallKeyBoardView(
                Modifier
                    .weight(1F)
                    .fillMaxSize(), navController, showCloseView
            )


            loadCallKeyDetailView(
                Modifier
                    .weight(1F)
                    .fillMaxSize(), navController
            )

        }
    }
}


@Composable
fun loadCallKeyDetailView(modifier: Modifier, navController: NavController) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (callPhoneNumber, callState, keyBoardImage, text) = createRefs()
        Text(
            text = "13718226523",
            fontSize = H4,
            color = Color.White,
            modifier = Modifier
                .constrainAs(callPhoneNumber) {
                    top.linkTo(parent.top, 80.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

        )
        Text(
            text = "通话中",
            fontSize = H6,
            color = Color.White,
            modifier = Modifier
                .constrainAs(callState) {
                    top.linkTo(callPhoneNumber.bottom, 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }

        )
        Text(
            text = "拨号键盘", fontSize = H6,
            color = Color.White, modifier = Modifier.constrainAs(text) {
                bottom.linkTo(parent.bottom, 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_becall),
            contentDescription = null,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .constrainAs(keyBoardImage) {
                    bottom.linkTo(text.top, 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clickable {
                    Toast
                        .makeText(navController.context, "拨号键盘", Toast.LENGTH_SHORT)
                        .show()

                }
        )
    }

}

@Composable
fun loadCallKeyBoardView(
    modifier: Modifier,
    navController: NavController,
    showClose: MutableState<Boolean>
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (topCloseImage, inputText, number1, number1BottomEnglish,
            number2, number2BottomEnglish,
            number3, number3BottomEnglish,
            number4,
            bottomKeyBoard, callImage, inputBackspace
        ) = createRefs()
        if (showClose.value) {
            Image(
                painter = painterResource(id = R.drawable.iv_input_clear),
                contentDescription = null,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .constrainAs(topCloseImage) {
                        top.linkTo(parent.top, 20.dp)
                        start.linkTo(parent.start, 20.dp)
                    }
                    .clickable {
//                            navController.popBackStack()

                    }
            )
        }
        val textValue = remember {
            mutableStateOf("137")//设置默认输入值为空
        }
        val textString = remember {
            StringBuilder()
        }
        val strIndex = remember {
            mutableStateOf(1)
        }
        Text(
            text = textValue.value,
            fontSize = H3,
            color = Color.White,
            modifier = Modifier.constrainAs(inputText) {
                top.linkTo(parent.top, 50.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })


        /*TextField(
            value = textValue.value,
            onValueChange = {
                textValue.value = it
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.constrainAs(textField) {
                top.linkTo(topCloseImage.bottom, 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )*/

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(25.dp)
            .constrainAs(number1) {
                top.linkTo(inputText.bottom, 40.dp)
            }) {
            Text(
                text = "1",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "1",
                            textValue,
                            navController,
                            strIndex
                        )

                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = "2",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "2",
                            textValue,
                            navController,
                            strIndex
                        )
                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = "3",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "3",
                            textValue,
                            navController,
                            strIndex
                        )

                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = "4",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "4",
                            textValue,
                            navController,
                            strIndex
                        )
                    },
                textAlign = TextAlign.Center
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp)
            .constrainAs(number1BottomEnglish) {
                top.linkTo(number1.bottom, 5.dp)
            }) {
            Text(
                text = "",
                fontSize = H6,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = "ABC",
                fontSize = H6,
                color = Color.White,
                modifier = Modifier
                    .weight(1F),
                textAlign = TextAlign.Center
            )
            Text(
                text = "DEF",
                fontSize = H6,
                color = Color.White,
                modifier = Modifier
                    .weight(1F),
                textAlign = TextAlign.Center
            )
            Text(
                text = "GHI",
                fontSize = H6,
                color = Color.White,
                modifier = Modifier
                    .weight(1F),
                textAlign = TextAlign.Center
            )
        }



        Row(modifier = Modifier
            .fillMaxWidth()
            .height(27.dp)
            .constrainAs(number2) {
                top.linkTo(number1BottomEnglish.bottom, 40.dp)
            }) {
            Text(
                text = "5",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "5",
                            textValue,
                            navController,
                            strIndex
                        )

                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = "6",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "6",
                            textValue,
                            navController,
                            strIndex
                        )

                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = "7",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "7",
                            textValue,
                            navController,
                            strIndex
                        )

                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = "8",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "8",
                            textValue,
                            navController,
                            strIndex
                        )
                    },
                textAlign = TextAlign.Center
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp)
            .constrainAs(number2BottomEnglish) {
                top.linkTo(number2.bottom, 5.dp)
            }) {
            Text(
                text = "JKL",
                fontSize = H6,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = "MNO",
                fontSize = H6,
                color = Color.White,
                modifier = Modifier
                    .weight(1F),
                textAlign = TextAlign.Center
            )
            Text(
                text = "PQRS",
                fontSize = H6,
                color = Color.White,
                modifier = Modifier
                    .weight(1F),
                textAlign = TextAlign.Center
            )
            Text(
                text = "TUV",
                fontSize = H6,
                color = Color.White,
                modifier = Modifier
                    .weight(1F),
                textAlign = TextAlign.Center
            )
        }



        Row(modifier = Modifier
            .fillMaxWidth()
            .height(27.dp)
            .constrainAs(number3) {
                top.linkTo(number2BottomEnglish.bottom, 40.dp)
            }) {
            Text(
                text = "9",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "9",
                            textValue,
                            navController,
                            strIndex
                        )
                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = "0",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "0",
                            textValue,
                            navController,
                            strIndex
                        )
                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = "*",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "*",
                            textValue,
                            navController,
                            strIndex
                        )
                    }
                    .padding(0.dp, 5.dp, 0.dp, 0.dp), textAlign = TextAlign.Center
            )
            Text(
                text = "#",
                fontSize = H3,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        assignmentPhoneNumberText(
                            textString,
                            "#",
                            textValue,
                            navController,
                            strIndex
                        )
                    },
                textAlign = TextAlign.Center
            )
        }


        Row(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp)
            .constrainAs(number3BottomEnglish) {
                top.linkTo(number3.bottom, 5.dp)
            }) {
            Text(
                text = "WXYZ",
                fontSize = H7,
                color = Color.White,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                    },
                textAlign = TextAlign.Center
            )
            Text(
                text = "+",
                fontSize = H6,
                color = Color.White,
                modifier = Modifier
                    .weight(1F),
                textAlign = TextAlign.Center
            )
            Text(
                text = "",
                fontSize = H6,
                color = Color.White,
                modifier = Modifier
                    .weight(1F),
                textAlign = TextAlign.Center
            )
            Text(
                text = "",
                fontSize = H6,
                color = Color.White,
                modifier = Modifier
                    .weight(1F),
                textAlign = TextAlign.Center
            )
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .constrainAs(number4) {
                top.linkTo(number3BottomEnglish.bottom, 50.dp)
            }) {
            Image(
                painter = painterResource(id = R.drawable.ic_becall),
                contentDescription = null,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .weight(1F)

            )

            Image(
                painter = painterResource(id = R.drawable.ic_becall),
                contentDescription = null,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .weight(1F)
                    .clickable {
                        Toast
                            .makeText(
                                navController.context,
                                "拨打电话号码：  " + textString.toString(),
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_becall),
                contentDescription = null,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .weight(1F)
                    .clickable {

                        if (textString.length == 0) {

                            return@clickable
                        }
                        textString.deleteCharAt(strIndex.value - 1)
                        strIndex.value = textString.length
                        textValue.value = textString.toString()
                        Log.v("CallBoardPage", " str: " + textString.toString())

                    }

            )

        }

    }
}

fun assignmentPhoneNumberText(
    textString: StringBuilder,
    phoneText: String,
    textValue: MutableState<String>,
    navController: NavController,
    strIndex: MutableState<Int>,
) {
    if (textString.toString().length >= 11) {
        Toast
            .makeText(navController.context, "号码输入格式不正确", Toast.LENGTH_SHORT)
            .show()
        return
    }

    textString.append(phoneText)
    textValue.value = textString.toString()
    strIndex.value = textString.length
}




