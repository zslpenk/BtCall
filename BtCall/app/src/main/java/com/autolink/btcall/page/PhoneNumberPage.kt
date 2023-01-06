package com.autolink.btcall.page

import android.view.LayoutInflater
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.autolink.btcall.R
import com.autolink.btcall.data.adapter.PhoneAdapter
import com.autolink.btcall.data.bean.PhoneData

@Composable
fun PhoneNumberPage(navController: NavController) {
    val phoneData = mutableListOf<PhoneData>()
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("123", 1, "1997-01-10"))
    phoneData.add(PhoneData("1115", 1, "1997-01-10"))
    phoneData.add(PhoneData("13788524761", 1, "1997-01-10"))
    loadPhoneData()
}

@Composable
fun loadPhoneData() {
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
