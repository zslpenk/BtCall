package com.autolink.btcall.utils

import android.app.Activity
import android.view.View
import android.view.WindowManager

fun transparentStatusBar(activity: Activity) {
    with(activity) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        val option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        val visible = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility = option or visible
        window.statusBarColor = android.graphics.Color.TRANSPARENT
    }
}