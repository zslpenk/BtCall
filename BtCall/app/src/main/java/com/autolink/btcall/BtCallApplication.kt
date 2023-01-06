package com.autolink.btcall

import android.app.Application
import android.content.Context

class BtCallApplication : Application() {
    companion object {
        lateinit var applicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        BtCallApplication.applicationContext = this
    }

}