package com.autolink.btcall.data.bean

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import java.util.function.Predicate


data class ContractData(
    var icon: String? = "110",
    var name: String? = "王伯阳",
    var contractItem: MutableList<ContractItem>?
)

data class ContractItem(
    var telType: String? = "电话",
    var teNumber: String? = "王伯阳"
)

