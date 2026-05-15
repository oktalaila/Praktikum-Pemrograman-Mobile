package com.example.wisatacomposeactivity.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wisatacomposeactivity.data.WisataData
import com.example.wisatacomposeactivity.model.Wisata
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class WisataViewModel(val title: String) : ViewModel() {
    private val _wisataList = MutableStateFlow<List<Wisata>>(emptyList())
    val wisataList: StateFlow<List<Wisata>> = _wisataList.asStateFlow()

    private val _selectedWisata = MutableStateFlow<Wisata?>(null)
    val selectedWisata: StateFlow<Wisata?> = _selectedWisata.asStateFlow()

    init {
        _wisataList.value = WisataData.listWisata
        Timber.d("LOG: Data Wisata dimuat. Jumlah: ${_wisataList.value.size}")
    }

    fun onWisataClicked(wisata: Wisata) {
        Timber.d("LOG: Item dipilih: ${wisata.nama}")
        _selectedWisata.value = wisata
    }

    fun logMapsClicked(nama: String) {
        Timber.d("LOG: Tombol MAPS diklik untuk: $nama")
    }

    fun clearSelection() {
        _selectedWisata.value = null
    }
}

class WisataViewModelFactory(private val title: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WisataViewModel(title) as T
    }
}