package io.github.hidroh.splitme

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val padding = (24 * resources.displayMetrics.density).toInt()
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(padding, padding, padding, padding)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        val title = TextView(this).apply {
            text = getString(R.string.app_name)
            textSize = 26f
        }
        val description = TextView(this).apply {
            text = getString(R.string.setup_instructions)
            textSize = 16f
            setPadding(0, padding / 2, 0, padding)
        }
        val settingsButton = Button(this).apply {
            text = getString(R.string.open_accessibility_settings)
            setOnClickListener {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Accessibility service disclosure")
                    .setMessage(
                        "SplitMe uses Android's Accessibility Service only to request the system split-screen toggle when you tap its Quick Settings tile. It does not read screen content, collect personal data, or send data to a server. You must enable the service yourself in Android Settings. You can cancel if you do not agree."
                    )
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Continue to Settings") { _, _ ->
                        startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                    }
                    .show()
            }
        }
        layout.addView(title)
        layout.addView(description)
        layout.addView(settingsButton)
        setContentView(layout)
    }
}
