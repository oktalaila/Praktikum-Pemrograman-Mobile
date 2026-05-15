package com.example.wisatacomposeactivity.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.wisatacomposeactivity.model.Wisata

@Composable
fun DetailScreen(wisata: Wisata, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White).verticalScroll(rememberScrollState())) {
        Box {
            Image(
                painter = painterResource(wisata.gambarRes),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(380.dp),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = onBack,
                modifier = Modifier.padding(16.dp).background(Color.White.copy(0.7f), RoundedCornerShape(50.dp))
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.Black)
            }
        }
        Column(modifier = Modifier.padding(24.dp)) {
            Text(wisata.nama, fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            Text(wisata.lokasiTahun, fontSize = 16.sp, color = Color(0xFF6750A4), fontWeight = FontWeight.SemiBold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp), thickness = 1.dp, color = Color(0xFFF0F0F0))
            Text("Deskripsi Destinasi", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = wisata.deskripsi, fontSize = 15.sp, lineHeight = 24.sp, color = Color.DarkGray)
        }
    }
}