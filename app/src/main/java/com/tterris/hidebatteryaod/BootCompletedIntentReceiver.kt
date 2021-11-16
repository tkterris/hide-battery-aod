package com.tterris.hidebatteryaod

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootCompletedIntentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startService(Intent(context, OverlayService::class.java))
    }
}