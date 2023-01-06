package com.autolink.btcall.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.autolink.btcall.BtCallApplication
import com.autolink.btcall.utils.CallUtils
import com.autolink.btcall.utils.ContractsUtils

class ContractsViewModel : ViewModel() {

    fun getContract() {
        val list = ContractsUtils.getAllContracts(BtCallApplication.applicationContext)
        Log.d("zsl","ContractsUtils.getAllContracts")
        list.forEach {
            Log.d("zsl","ContractInfo = $it")
        }
    }

    fun getCallLog() {
        val list = CallUtils.getCallLogList(BtCallApplication.applicationContext)
        Log.d("zsl","CallUtils.getCallLogList")
        list.forEach {
            Log.d("zsl", "CallLogInfo = $it")
        }
    }
}