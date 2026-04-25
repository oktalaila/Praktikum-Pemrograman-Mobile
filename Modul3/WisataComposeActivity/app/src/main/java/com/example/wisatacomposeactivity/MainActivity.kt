package com.example.wisatacomposeactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowInsetsControllerCompat
import com.example.wisatacomposeactivity.data.WisataData
import com.example.wisatacomposeactivity.model.Wisata
import com.example.wisatacomposeactivity.ui.*
import com.example.wisatacomposeactivity.ui.theme.WisataComposeActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightNavigationBars = true
        windowInsetsController.isAppearanceLightStatusBars = true

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
                            MainList(
                                list = WisataData.listWisata,
                                onDetail = { wisataTerpilih = it }
                            )
                        } else {
                            DetailScreen(
                                wisata = wisataTerpilih!!,
                                onBack = { wisataTerpilih = null }
                            )
                        }
                    }
                }
            }
        }
    }
}