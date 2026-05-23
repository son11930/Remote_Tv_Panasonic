package com.example.tvremote

import android.content.Context
import android.hardware.ConsumerIrManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var irManager: ConsumerIrManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        irManager = getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager?

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 100, 50, 50)
            gravity = android.view.Gravity.CENTER_HORIZONTAL
        }

        val statusText = TextView(this).apply {
            text = if (irManager?.hasIrEmitter() == true) {
                "✅ พบ IR Blaster พร้อมใช้งาน!"
            } else {
                "❌ ไม่พบ IR Blaster ในเครื่องนี้"
            }
            textSize = 20f
            setPadding(0, 0, 0, 100)
            gravity = android.view.Gravity.CENTER
        }
        layout.addView(statusText)

        val btnVolUp = Button(this).apply {
            text = "เพิ่มเสียง (+)"
            textSize = 30f
            setPadding(0, 50, 0, 50)
            setOnClickListener { sendIrCode("VOL_UP") }
        }
        layout.addView(btnVolUp, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply { setMargins(0, 0, 0, 50) })

        val btnVolDown = Button(this).apply {
            text = "ลดเสียง (-)"
            textSize = 30f
            setPadding(0, 50, 0, 50)
            setOnClickListener { sendIrCode("VOL_DOWN") }
        }
        layout.addView(btnVolDown, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 
            LinearLayout.LayoutParams.WRAP_CONTENT
        ))

        setContentView(layout)
    }

    private fun sendIrCode(command: String) {
        if (irManager?.hasIrEmitter() != true) {
            Toast.makeText(this, "เครื่องนี้ไม่มีตัวส่งสัญญาณ IR", Toast.LENGTH_SHORT).show()
            return
        }

        // คลื่นความถี่ของทีวี Panasonic
        val freq = 38000 
        
        // ชุดรหัสสัญญาณ (Pulse array) ที่ถูกต้องแม่นยำสำหรับ Panasonic 48-bit protocol
        val pattern = if (command == "VOL_UP") {
            intArrayOf(3456, 1728, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432)
        } else {
            intArrayOf(3456, 1728, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 1296, 432, 432, 432, 432, 432, 432, 432, 432, 432)
        }

        try {
            irManager?.transmit(freq, pattern)
        } catch (e: Exception) {
            Log.e("TV_REMOTE", "Error transmitting IR", e)
        }
    }
}
