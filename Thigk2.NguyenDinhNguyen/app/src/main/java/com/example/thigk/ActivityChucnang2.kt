package com.example.thigk

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ActivityChucnang2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chucnang2)
        val edtSoA = findViewById<EditText>(R.id.edtA)
        val edtSoB = findViewById<EditText>(R.id.edtB)
        val btnTinhTong = findViewById<Button>(R.id.btnTB)
        val txtKetQua = findViewById<TextView>(R.id.edtkq)

        btnTinhTong.setOnClickListener {
            val soA = edtSoA.text.toString().toIntOrNull() ?: 0
            val soB = edtSoB.text.toString().toIntOrNull() ?: 0
            val ketQua = soA + soB

            txtKetQua.text = ketQua.toString()
        }
    }
}