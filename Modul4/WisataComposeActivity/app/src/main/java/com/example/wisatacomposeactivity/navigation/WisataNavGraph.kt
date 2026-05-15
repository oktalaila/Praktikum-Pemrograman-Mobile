package com.example.wisatacomposeactivity.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.wisatacomposeactivity.ui.screens.DetailScreen
import com.example.wisatacomposeactivity.ui.screens.HomeScreen
import com.example.wisatacomposeactivity.ui.viewmodel.WisataViewModel

@Composable
fun WisataNavGraph(viewModel: WisataViewModel) {
    val selectedWisata by viewModel.selectedWisata.collectAsState()
    val listWisata by viewModel.wisataList.collectAsState()

    val currentSelected = selectedWisata

    if (currentSelected == null) {
        HomeScreen(
            list = listWisata,
            onDetail = { viewModel.onWisataClicked(it) },
            onMapsClick = { viewModel.logMapsClicked(it) }
        )
    } else {
        DetailScreen(
            wisata = currentSelected,
            onBack = { viewModel.clearSelection() }
        )
    }
}