package io.github.hidroh.splitme

import android.accessibilityservice.AccessibilityService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class SplitToggleService : AccessibilityService() {
  private val localBroadcastManager
    get() = LocalBroadcastManager.getInstance(this)

  private val receiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
      toggleSplitScreen()
    }
  }

  override fun onCreate() {
    super.onCreate()
    localBroadcastManager.registerReceiver(receiver, IntentFilter(ACTION_TOGGLE_SPLIT_SCREEN))
  }

  // Quick Settings tiles send the action directly to the enabled service.
  // This is more reliable than bouncing the request through a temporary Activity
  // and a process-local broadcast, especially on OEM Android builds.
  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    if (intent?.action == ACTION_TOGGLE_SPLIT_SCREEN) {
      toggleSplitScreen()
    }
    return super.onStartCommand(intent, flags, startId)
  }

  private fun toggleSplitScreen() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      val success = performGlobalAction(GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN)
      if (!success) {
        Toast.makeText(
            this,
            R.string.split_screen_toggle_failed,
            Toast.LENGTH_LONG
        ).show()
      }
    } else {
      Toast.makeText(
          this,
          R.string.split_screen_unsupported,
          Toast.LENGTH_LONG
      ).show()
    }
  }

  override fun onInterrupt() { }

  override fun onAccessibilityEvent(event: AccessibilityEvent) { }

  override fun onDestroy() {
    localBroadcastManager.unregisterReceiver(receiver)
    super.onDestroy()
  }
}
