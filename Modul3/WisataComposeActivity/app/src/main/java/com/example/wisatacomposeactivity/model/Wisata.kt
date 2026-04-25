package com.example.wisatacomposeactivity.model

import androidx.annotation.DrawableRes

data class Wisata(
    val id: Int,
    val nama: String,
    val lokasiTahun: String,
    val deskripsi: String,
    @DrawableRes val gambarRes: Int,
    val gmapsUrl: String
)