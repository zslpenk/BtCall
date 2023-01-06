package com.autolink.btcall.utils

import android.content.Context
import android.net.Uri
import com.autolink.btcall.data.contract.ContractInfo
import kotlin.math.max

object ContractsUtils {

    fun getAllContracts(context: Context): List<ContractInfo> {
        val list = ArrayList<ContractInfo>()

        val resolver = context.contentResolver

        //load url
        val rawContacts = Uri.parse("content://com.android.contacts/raw_contacts")
        val data = Uri.parse("content://com.android.contacts/data")

        val cursor = resolver.query(rawContacts, arrayOf("contact_id"), null, null, null)
        cursor ?: return list

        while (cursor.moveToNext()) {
            val index = cursor.getColumnIndex("contact_id")
            if (index == -1) break
            val id = cursor.getString(index)
            var name = ""
            var phone = ""

            val item = resolver.query(data, arrayOf("mimetype", "data1"), "raw_contact_id=?", arrayOf(id), null)
            item ?: break

            while (item.moveToNext()) {
                val mimeType = item.getString(max(item.getColumnIndex("mimetype"), 0))
                val data1 = item.getString(max(item.getColumnIndex("data1"), 0))

                if ("vnd.android.cursor.item/name" == mimeType) {
                    name = data1
                } else if ("vnd.android.cursor.item/phone_v2" == mimeType){
                    //the phone number has blank.
                    phone = data1.replace(" ","");
                }
            }

            val info = ContractInfo(id, name, phone)
            item.close()
            list.add(info)
        }

        cursor.close()
        return list
    }
}