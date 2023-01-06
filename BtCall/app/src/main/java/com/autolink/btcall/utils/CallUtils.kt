package com.autolink.btcall.utils

import android.content.Context
import android.provider.CallLog
import com.autolink.btcall.data.call.CallLogInfo
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

object CallUtils {
    private val logColumns = arrayOf(CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.TYPE)

    fun getCallLogList(context: Context): List<CallLogInfo> {
        val callLogList = ArrayList<CallLogInfo>()
        val cursor = context.contentResolver.query(CallLog.Calls.CONTENT_URI, logColumns, null, null, CallLog.Calls.DEFAULT_SORT_ORDER)

        cursor?.let {
            while (it.moveToNext()) {
                val name = cursor.getString(max(cursor.getColumnIndex(logColumns[0]), 0))
                val number = cursor.getString(max(cursor.getColumnIndex(logColumns[1]), 0))
                val date = cursor.getString(max(cursor.getColumnIndex(logColumns[2]), 0))

                val formatDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date(date))
                callLogList.add(CallLogInfo(name, number, formatDate))
            }


            it.close()
        }

        return callLogList
    }
}