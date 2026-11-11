package com.ayansh.hosting

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 32, 28, 32)
            setBackgroundColor(Color.rgb(10, 17, 29))
        }
        fun label(text: String, size: Float, color: Int, bold: Boolean = false) =
            TextView(this).apply {
                this.text = text
                textSize = size
                setTextColor(color)
                if (bold) setTypeface(null, android.graphics.Typeface.BOLD)
                gravity = Gravity.CENTER
                setPadding(8, 12, 8, 12)
            }
        root.addView(label("AYANSH HOSTING", 25f, Color.WHITE, true))
        root.addView(label("Your Minecraft cloud control panel", 14f, Color.LTGRAY))
        root.addView(label("SERVER: NOT CONNECTED", 18f, Color.rgb(255,190,70), true))
        root.addView(label("Cloud API and Android app scaffold is ready.\nA real Minecraft host agent and compute server are still required before Start / Stop / Console can work.", 14f, Color.LTGRAY))
        root.addView(label("Next: connect a secure host agent", 15f, Color.rgb(90,220,160), true))
        setContentView(root)
    }
}
