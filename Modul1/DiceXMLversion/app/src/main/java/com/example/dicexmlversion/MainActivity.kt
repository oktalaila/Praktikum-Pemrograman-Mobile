package com.example.dicexmlversion

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivDadu1: ImageView = findViewById(R.id.iv_dadu1)
        val ivDadu2: ImageView = findViewById(R.id.iv_dadu2)
        val btnRoll: Button = findViewById(R.id.btn_roll)
        val tvHasil: TextView = findViewById(R.id.tv_hasil)

        btnRoll.setOnClickListener {
            kocokDadu(ivDadu1, ivDadu2, tvHasil)
        }
    }

    private fun kocokDadu(dadu1: ImageView, dadu2: ImageView, teksPesan: TextView) {
        val hasil1 = (1..6).random()
        val hasil2 = (1..6).random()

        dadu1.setImageResource(ambilGambarDadu(hasil1))
        dadu2.setImageResource(ambilGambarDadu(hasil2))

        if (hasil1 == hasil2) {
            teksPesan.text = "Selamat, anda dapat dadu double!"
        } else {
            teksPesan.text = "Anda belum beruntung!"
        }
    }

    private fun ambilGambarDadu(angka: Int): Int {
        return when (angka) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_1
        }
    }
}