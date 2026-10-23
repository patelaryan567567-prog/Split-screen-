package io.github.hidroh.splitme

import android.accessibilityservice.AccessibilityService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast
import android.view.accessibility.AccessibilityEvent
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class SplitToggleService : AccessibilityService() {
  private val localBroadcastManager
    get() = LocalBroadcastManager.getInstance(this)
  private val receiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
      val actionAvailable = getSystemActions().any {
        it.id == GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN
      }
      if (!actionAvailable) {
        Toast.makeText(
            this@SplitToggleService,
            R.string.split_screen_unsupported,
            Toast.LENGTH_LONG
        ).show()
        return
      }

      val success = performGlobalAction(GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN)
      if (!success) {
        Toast.makeText(
            this@SplitToggleService,
            R.string.split_screen_toggle_failed,
            Toast.LENGTH_LONG
        ).show()
      }
    }
  }

  override fun onCreate() {
    super.onCreate()
    localBroadcastManager.registerReceiver(receiver, IntentFilter(ACTION_TOGGLE_SPLIT_SCREEN))
  }

  override fun onInterrupt() { }

  override fun onAccessibilityEvent(event: AccessibilityEvent) { }

  override fun onDestroy() {
    localBroadcastManager.unregisterReceiver(receiver)
    super.onDestroy()
  }
}
