package com.example.wisatacomposeactivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsControllerCompat
import com.example.wisatacomposeactivity.ui.theme.WisataComposeActivityTheme

data class Wisata(
    val id: Int,
    val nama: String,
    val lokasiTahun: String,
    val deskripsi: String,
    val gambarRes: Int,
    val gmapsUrl: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightNavigationBars = true
        windowInsetsController.isAppearanceLightStatusBars = true

        val listWisata = listOf(
            Wisata(1, "Pantai Tanjung Dewa", "Panyipatan, 2024", "Destinasi eksotis dengan pesona tebing karang dan pulau kecil untuk menikmati matahari terbenam.", R.drawable.img_sunset, "https://maps.app.goo.gl/Bz29iWYcSSTk9Uas7?g_st=ac"),
            Wisata(2, "Jalan Slamet Riyadi", "Solo, 2024", "Jalur ikonik di jantung kota Solo dengan suasana pedestrian yang rindang dan bersejarah.", R.drawable.img_slamet_riyadi, "https://maps.app.goo.gl/kva3qteZGFuzyWrb7?g_st=ac"),
            Wisata(3, "Taman Budaya Kalsel", "Banjarmasin, 2024", "Pusat pelestarian seni Banjar yang sering menyuguhkan pameran lukisan dan pertunjukan teater estetik.", R.drawable.img_gallery, "https://maps.app.goo.gl/hGCggndTXpm3bv9L9?g_st=ac"),
            Wisata(4, "Stadion Pertasi Kencana", "Pelaihari, 2024", "Kawasan olahraga megah di Tanah Laut dengan area terbuka yang luas dan menyegarkan.", R.drawable.img_stadium, "https://maps.app.goo.gl/1VwJYe8Y9Lp3EFXb8?g_st=ac"),
            Wisata(5, "Bukit Gelatik", "Tanah Laut, 2024", "Nikmati sensasi negeri di atas awan dengan panorama perbukitan hijau yang menyejukkan mata.", R.drawable.img_mountain, "https://maps.app.goo.gl/zhEyHgNqrqqqjsyv5?g_st=ac"),
            Wisata(6, "Stasiun Tugu Jogja", "Yogyakarta, 2024", "Gerbang masuk penuh nostalgia dengan arsitektur klasik yang megah di jantung kota Jogja.", R.drawable.img_station, "https://maps.app.goo.gl/VBQw3h5E3cGFpirx8?g_st=ac")
        )

        setContent {
            WisataComposeActivityTheme {
                var wisataTerpilih by remember { mutableStateOf<Wisata?>(null) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFFF8F8F8),
                    contentWindowInsets = WindowInsets.systemBars
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        if (wisataTerpilih == null) {
                            MainList(listWisata, onDetail = { wisataTerpilih = it })
                        } else {
                            DetailScreen(wisataTerpilih!!, onBack = { wisataTerpilih = null })
                        }
                    }
                }
            }
        }
    }
}

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
            modifier = Modifier
                .padding(12.dp)
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = wisata.gambarRes),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = wisata.nama,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            maxLines = 1,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = wisata.lokasiTahun,
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                    }
                    Text("Tentang:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
                    Text(
                        text = wisata.deskripsi,
                        fontSize = 13.sp,
                        color = Color.DarkGray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
            Text(
                text = wisata.deskripsi,
                fontSize = 15.sp, lineHeight = 24.sp, color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}