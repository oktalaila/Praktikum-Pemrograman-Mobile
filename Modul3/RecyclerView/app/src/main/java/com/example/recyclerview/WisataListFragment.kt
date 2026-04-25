package com.example.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.FragmentWisataListBinding

class WisataListFragment : Fragment() {

    private var _binding: FragmentWisataListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWisataListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listWisata = listOf(
            Wisata(1, "Pantai Tanjung Dewa", "Panyipatan, 2024", "Destinasi eksotis dengan pesona tebing karang dan pulau kecil untuk menikmati matahari terbenam.", R.drawable.img_sunset, "https://maps.app.goo.gl/Bz29iWYcSSTk9Uas7?g_st=ac"),
            Wisata(2, "Jalan Slamet Riyadi", "Solo, 2024", "Jalur ikonik di jantung kota Solo dengan suasana pedestrian yang rindang dan bersejarah.", R.drawable.img_slamet_riyadi, "https://maps.app.goo.gl/kva3qteZGFuzyWrb7?g_st=ac"),
            Wisata(3, "Taman Budaya Kalsel", "Banjarmasin, 2024", "Pusat pelestarian seni Banjar yang sering menyuguhkan pameran lukisan dan pertunjukan teater estetik.", R.drawable.img_gallery, "https://maps.app.goo.gl/hGCggndTXpm3bv9L9?g_st=ac"),
            Wisata(4, "Stadion Pertasi Kencana", "Pelaihari, 2024", "Kawasan olahraga megah di Tanah Laut dengan area terbuka yang luas dan menyegarkan.", R.drawable.img_stadium, "https://maps.app.goo.gl/1VwJYe8Y9Lp3EFXb8?g_st=ac"),
            Wisata(5, "Bukit Gelatik", "Tanah Laut, 2024", "Nikmati sensasi negeri di atas awan dengan panorama perbukitan hijau yang menyejukkan mata.", R.drawable.img_mountain, "https://maps.app.goo.gl/zhEyHgNqrqqqjsyv5?g_st=ac"),
            Wisata(6, "Stasiun Tugu Jogja", "Yogyakarta, 2024", "Gerbang masuk penuh nostalgia dengan arsitektur klasik yang megah di jantung kota Jogja.", R.drawable.img_station, "https://maps.app.goo.gl/VBQw3h5E3cGFpirx8?g_st=ac")
        )

        val adapterWisata = WisataAdapter(listWisata)

        binding.rvWisata.layoutManager = LinearLayoutManager(context)
        binding.rvWisata.adapter = adapterWisata
        binding.rvWisata.isNestedScrollingEnabled = false

        binding.rvFeatured.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvFeatured.adapter = adapterWisata
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}