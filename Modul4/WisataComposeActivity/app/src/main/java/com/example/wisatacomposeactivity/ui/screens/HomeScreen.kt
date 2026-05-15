package com.example.wisatacomposeactivity.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wisatacomposeactivity.model.Wisata
import com.example.wisatacomposeactivity.ui.components.WisataCard

@Composable
fun HomeScreen(list: List<Wisata>, onDetail: (Wisata) -> Unit, onMapsClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
        item {
            Text("Destinasi Populer", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(list) { WisataCard(it, modifier = Modifier.width(310.dp), onDetail, onMapsClick) }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text("Semua Destinasi", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(list) { WisataCard(it, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), onDetail, onMapsClick) }
    }
}