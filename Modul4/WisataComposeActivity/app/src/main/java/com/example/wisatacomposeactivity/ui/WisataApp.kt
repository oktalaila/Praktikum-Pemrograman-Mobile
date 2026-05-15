package com.example.wisatacomposeactivity.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wisatacomposeactivity.navigation.WisataNavGraph
import com.example.wisatacomposeactivity.ui.viewmodel.WisataViewModel
import com.example.wisatacomposeactivity.ui.viewmodel.WisataViewModelFactory

@Composable
fun WisataApp() {
    val viewModel: WisataViewModel = viewModel(factory = WisataViewModelFactory("Wisata Banua"))

    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            WisataNavGraph(viewModel = viewModel)
        }
    }
}