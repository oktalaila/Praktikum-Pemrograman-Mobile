package com.example.recyclerview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class WisataViewModel(val title: String) : ViewModel() {

    private val _wisataList = MutableStateFlow<List<Wisata>>(emptyList())
    val wisataList: StateFlow<List<Wisata>> = _wisataList.asStateFlow()

    init {
        _wisataList.value = listOf(
            Wisata(1, "Pantai Tanjung Dewa", "Panyipatan, 2024", "Destinasi eksotis dengan pesona tebing karang...", R.drawable.img_sunset, "https://maps.google.com/?q=Pantai+Tanjung+Dewa"),
            Wisata(2, "Jalan Slamet Riyadi", "Solo, 2024", "Jalur ikonik di jantung kota Solo...", R.drawable.img_slamet_riyadi, "https://maps.google.com/?q=Jalan+Slamet+Riyadi+Solo"),
            Wisata(3, "Taman Budaya Kalsel", "Banjarmasin, 2024", "Pusat pelestarian seni Banjar...", R.drawable.img_gallery, "https://maps.google.com/?q=Taman+Budaya+Kalsel"),
            Wisata(4, "Stadion Pertasi Kencana", "Pelaihari, 2024", "Kawasan olahraga megah di Tanah Laut...", R.drawable.img_stadium, "https://maps.google.com/?q=Stadion+Pertasi+Kencana"),
            Wisata(5, "Bukit Gelatik", "Tanah Laut, 2024", "Panorama perbukitan hijau yang menyejukkan...", R.drawable.img_mountain, "https://maps.google.com/?q=Bukit+Gelatik"),
            Wisata(6, "Stasiun Tugu Jogja", "Yogyakarta, 2024", "Gerbang masuk penuh nostalgia...", R.drawable.img_station, "https://maps.google.com/?q=Stasiun+Tugu+Yogyakarta")
        )
        Timber.d("LOG: Data Wisata ($title) dimuat di ViewModel. Jumlah: ${_wisataList.value.size}")
    }

    fun logDetailClicked(nama: String) {
        Timber.d("LOG: Berpindah ke Detail Wisata: $nama")
    }

    fun logMapsClicked(nama: String) {
        Timber.d("LOG: Tombol MAPS diklik untuk: $nama")
    }
}

class WisataViewModelFactory(private val title: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WisataViewModel::class.java)) {
            return WisataViewModel(title) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}