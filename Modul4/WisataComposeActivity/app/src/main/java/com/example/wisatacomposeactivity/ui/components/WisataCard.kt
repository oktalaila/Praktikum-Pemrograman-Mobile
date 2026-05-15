package com.example.wisatacomposeactivity.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wisatacomposeactivity.model.Wisata

@Composable
fun WisataCard(wisata: Wisata, modifier: Modifier = Modifier, onDetail: (Wisata) -> Unit, onMapsClick: (String) -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp).height(IntrinsicSize.Min), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = wisata.gambarRes),
                contentDescription = null,
                modifier = Modifier.size(120.dp).clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f).fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
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
                        onMapsClick(wisata.nama) // LOG TIMBER
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(wisata.gmapsUrl))
                        context.startActivity(intent)
                    }) {
                        Text("MAPS", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6750A4))
                    }
                    Button(onClick = { onDetail(wisata) }, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4)), modifier = Modifier.height(36.dp)) {
                        Text("DETAIL", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}