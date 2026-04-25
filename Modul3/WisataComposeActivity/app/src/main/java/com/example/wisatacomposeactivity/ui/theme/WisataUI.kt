package com.example.wisatacomposeactivity.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import com.example.wisatacomposeactivity.model.Wisata

@Composable
fun MainList(list: List<Wisata>, onDetail: (Wisata) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text("Destinasi Populer", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(list) { WisataCard(it, modifier = Modifier.width(310.dp), onDetail) }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text("Semua Destinasi", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(list) { WisataCard(it, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), onDetail) }
    }
}

@Composable
fun WisataCard(wisata: Wisata, modifier: Modifier = Modifier, onDetail: (Wisata) -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp).height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = wisata.gambarRes),
                contentDescription = null,
                modifier = Modifier.size(120.dp).clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = wisata.nama, fontWeight = FontWeight.Bold, fontSize = 17.sp, maxLines = 1, modifier = Modifier.weight(1f))
                        Text(text = wisata.lokasiTahun, fontSize = 11.sp, color = Color.Gray)
                    }
                    Text("Tentang:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
                    Text(text = wisata.deskripsi, fontSize = 13.sp, color = Color.DarkGray, maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(wisata.gmapsUrl))
                        context.startActivity(intent)
                    }) {
                        Text("MAPS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6750A4))
                    }
                    Button(
                        onClick = { onDetail(wisata) },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4)),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text("DETAIL", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

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
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}