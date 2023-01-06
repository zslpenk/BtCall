package com.autolink.btcall.page

import android.util.Log
import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.autolink.btcall.R
import com.autolink.btcall.data.adapter.PhoneAdapter
import kotlinx.coroutines.launch
import java.lang.Math.abs

@Composable
fun ContractsPage(navController: NavController) {
//    Column {
//        Text(text = "Contracts Page", color = Color.Blue)
//        Button(onClick = {
//            navController.navigate(DestCallingPage) {
//                popUpTo(DestContractsPage) {}}
//        })
//        {
//            Text(text = "Go to Calling Page")
//        }
//
//    }
    ContractList()
}

@Preview
@Composable
fun ContractList() {
    val items = remember { LoremIpsum().values.first().split(" ").sortedBy { it.lowercase() } }
    val list = listOf(
        "A", "B", "C", "D",
        "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
        "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"
    )

    val headers = remember { list }

    Row(modifier = Modifier.background(Color.DarkGray)) {
        val listState = rememberLazyListState()
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
        ) {
            items(items) {
                Text(it, color = Color.White)
            }
        }
        val offsets = remember { mutableStateMapOf<Int, Float>() }
        var selectedHeaderIndex by remember { mutableStateOf(-1) }
        val scope = rememberCoroutineScope()

        fun updateSelectedIndexIfNeeded(offset: Float) {
            val index = offsets
                .mapValues { abs(it.value - offset) }
                .entries
                .minByOrNull { it.value }
                ?.key ?: return

            if (selectedHeaderIndex == index) return
            selectedHeaderIndex = index
            val selectedItemIndex = items.indexOfFirst { it.first().uppercase() == headers[selectedHeaderIndex] }
            scope.launch {
                listState.scrollToItem(selectedItemIndex)
            }
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight()
                .width(40.dp)
                .padding(top = 20.dp, bottom = 20.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        updateSelectedIndexIfNeeded(it.y)
                    }
                }
                .pointerInput(Unit) {
                    detectVerticalDragGestures { change, _ ->
                        updateSelectedIndexIfNeeded(change.position.y)
                    }
                }
        ) {
            headers.forEachIndexed { i, header ->
                Text(
                    header,
                    modifier = Modifier
                        .size(
                            if (selectedHeaderIndex == i) LetterWidth else NormalLetterWidth,
                            if (selectedHeaderIndex == i) LetterHight else NormalLetterHight
                        )
                        .background(
                            if (selectedHeaderIndex == i) Color.Blue else Color.Transparent,
                            if (selectedHeaderIndex == i) LetterShape else RectangleShape
                        )
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 4.dp)
                        .onGloballyPositioned {
                            offsets[i] = it.boundsInParent().center.y
                            Log.d("zsl", "onGloballyPositioned ${it.boundsInParent().center.y}")
                        },
                    color = Color.White,
                    fontSize = if (selectedHeaderIndex == i) FontSize else NormalFontSize,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}


@Composable
fun loadContractPageData() {
    var myRecyclerView: RecyclerView? = null
    var layoutManager: LinearLayoutManager? = null
    var listAdapter: PhoneAdapter? = null
    AndroidView(factory = {
        //加载AndroidView布局。
        LayoutInflater.from(it).inflate(R.layout.layout_android_view, null).apply {
            myRecyclerView = findViewById<RecyclerView>(R.id.phone_recycleview)
            layoutManager = LinearLayoutManager(it)
            listAdapter = PhoneAdapter(mutableListOf(), it)

        }
    }, modifier = Modifier.fillMaxSize()) {
        myRecyclerView!!.adapter = listAdapter
        myRecyclerView!!.layoutManager = layoutManager
    }
}


private val NormalLetterWidth = 20.dp
private val NormalLetterHight = 20.dp
private val LetterWidth = 40.dp
private val LetterHight = 40.dp
private val LetterShape = RoundedCornerShape(20.dp)
private val NormalFontSize = 13.sp
private val FontSize = 20.sp
