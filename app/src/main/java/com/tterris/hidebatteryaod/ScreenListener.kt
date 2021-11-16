package com.tterris.hidebatteryaod

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

object ScreenListener : BroadcastReceiver() {

    fun start(context: Context)  {
        val intentFilter: IntentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_USER_PRESENT)
        }
        context.registerReceiver(this, intentFilter)
    }

    fun stop(context: Context) {
        context.unregisterReceiver(this)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SCREEN_OFF -> OverlayService.INS?.showOverlay()
            Intent.ACTION_SCREEN_ON, Intent.ACTION_USER_PRESENT
                -> OverlayService.INS?.hideOverlay()
        }
    }
}