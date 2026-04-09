package com.example.cidecomposeversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cidecomposeversion.ui.theme.CideComposeversionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CideComposeversionTheme {
                DiceRollerApp()
            }
        }
    }
}

@Composable
fun DiceRollerApp() {
    var result1 by remember { mutableStateOf(0) }
    var result2 by remember { mutableStateOf(0) }

    val message = when {
        result1 == 0 -> "Tekan Roll untuk memulai"
        result1 == result2 -> "Selamat, anda dapat dadu double!"
        else -> "Anda belum beruntung!"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Row(verticalAlignment = Alignment.CenterVertically) {
            DiceImage(result = result1)
            Spacer(modifier = Modifier.width(16.dp))
            DiceImage(result = result2)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            result1 = (1..6).random()
            result2 = (1..6).random()
        }) {
            Text(text = "Roll")
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE))
                .padding(24.dp)
        ) {
            Text(
                text = message,
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun DiceImage(result: Int) {
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_0
    }

    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "Hasil dadu: $result",
        modifier = Modifier.size(150.dp)
    )
}