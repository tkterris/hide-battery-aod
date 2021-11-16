package com.tterris.hidebatteryaod

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent

class OverlayService : AccessibilityService() {
    companion object {
        var INS: OverlayService? = null
        var wm: WindowManager? = null
    }

    override fun onCreate() {
        INS = this
        wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        ScreenListener.start(this)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        INS = null
        hideOverlay()
        wm = null
        ScreenListener.stop(this)
        super.onDestroy()
    }

    fun showOverlay() {
        wm?.addView(view, layoutParams)
    }

    fun hideOverlay() {
        if (view.isAttachedToWindow) {
            wm?.removeViewImmediate(view)
        }
    }

    private val view by lazy {
        object : View(this) {
            init {
                setBackgroundColor(Color.BLACK)
            }
        }
    }
    private val layoutParams: WindowManager.LayoutParams
        get() = WindowManager.LayoutParams(
            wm?.currentWindowMetrics?.bounds?.width() ?: 2000, 250,
            0, 0,
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            , 0
        ).apply {
            format = PixelFormat.RGBA_8888
            gravity = Gravity.BOTTOM or Gravity.CENTER
        }

    override fun onInterrupt() {
        //do nothing
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        //do nothing
    }
}