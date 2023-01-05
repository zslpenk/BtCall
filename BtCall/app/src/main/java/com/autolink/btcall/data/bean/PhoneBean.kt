package com.autolink.btcall.data.bean

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import java.util.function.Predicate


data class PhoneData(
    var phoneName: String? = "110",
    var phoneState: Int = -1,
    var time: String? = "1997-01-01"
)