package com.ayansh.hosting

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.widget.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : Activity() {
    private lateinit var apiUrl: EditText
    private lateinit var token: EditText
    private lateinit var status: TextView
    private lateinit var output: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 36, 28, 24)
            setBackgroundColor(Color.rgb(10, 17, 29))
        }
        fun label(value: String, size: Float, color: Int = Color.WHITE) =
            TextView(this).apply { text=value; textSize=size; setTextColor(color); setPadding(0,12,0,12) }
        root.addView(label("AYANSH HOSTING", 26f))
        root.addView(label("Minecraft Cloud Control", 15f, Color.LTGRAY))
        apiUrl = EditText(this).apply { hint="Cloud API URL (https://...workers.dev)"; setSingleLine(true); setTextColor(Color.WHITE); setHintTextColor(Color.GRAY) }
        token = EditText(this).apply { hint="Panel API token"; setSingleLine(true); inputType=android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD; setTextColor(Color.WHITE); setHintTextColor(Color.GRAY) }
        root.addView(apiUrl); root.addView(token)
        status = label("Status: NOT CONNECTED", 18f, Color.rgb(255,190,70))
        root.addView(status)
        root.addView(Button(this).apply { text="CHECK SERVER STATUS"; setOnClickListener { checkStatus() } })
        output = label("Enter your HTTPS Worker URL and API token. A disconnected server is never shown as online.", 14f, Color.LTGRAY)
        root.addView(output)
        setContentView(ScrollView(this).apply { addView(root) })
    }

    private fun checkStatus() {
        val base = apiUrl.text.toString().trim().trimEnd('/')
        val secret = token.text.toString().trim()
        if (!base.startsWith("https://") || secret.isEmpty()) {
            status.text = "Status: CONFIGURATION REQUIRED"
            output.text = "Enter your HTTPS Cloudflare Worker URL and PANEL_API_TOKEN."
            return
        }
        status.text = "Status: CHECKING..."
        Thread {
            try {
                val connection = (URL("$base/api/status").openConnection() as HttpURLConnection).apply {
                    requestMethod = "GET"
                    connectTimeout = 8000
                    readTimeout = 8000
                    setRequestProperty("Authorization", "Bearer $secret")
                }
                val code = connection.responseCode
                val body = (if (code in 200..299) connection.inputStream else connection.errorStream).bufferedReader().use { it.readText() }
                runOnUiThread {
                    status.text = if (code == 200) "Status: API CONNECTED" else "Status: API ERROR ($code)"
                    output.text = body
                }
                connection.disconnect()
            } catch (e: Exception) {
                runOnUiThread { status.text="Status: CONNECTION FAILED"; output.text=e.message ?: "Network error" }
            }
        }.start()
    }
}
