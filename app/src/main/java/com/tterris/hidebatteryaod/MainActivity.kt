package com.tterris.hidebatteryaod

import android.app.Activity
import android.content.Intent

class MainActivity : Activity() {
    override fun onStart() {
        startService(Intent(this, OverlayService::class.java))
        super.onStart()
    }
}