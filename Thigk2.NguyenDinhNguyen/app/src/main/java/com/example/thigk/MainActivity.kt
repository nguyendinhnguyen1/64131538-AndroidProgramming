package com.example.thigk

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val btnCau1 = findViewById<Button>(R.id.btn1)
        val btnCau2 = findViewById<Button>(R.id.btn2)
        val btnCau3 = findViewById<Button>(R.id.btn3)
        val btnCau4 = findViewById<Button>(R.id.btn4)

        btnCau1.setOnClickListener {
            startActivity(Intent(this, ActivityChucnang2::class.java))
        }
        btnCau2.setOnClickListener {
            startActivity(Intent(this, ActivityChucnang3::class.java))
        }
        btnCau3.setOnClickListener {
            startActivity(Intent(this, ActivityChucnang4::class.java))
        }
        btnCau4.setOnClickListener {
            startActivity(Intent(this, ActivityAboutMe::class.java))
        }
    }
}
