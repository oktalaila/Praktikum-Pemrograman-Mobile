package com.example.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recyclerview.databinding.FragmentWisataDetailBinding

class WisataDetailFragment : Fragment() {

    private var _binding: FragmentWisataDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWisataDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nama = arguments?.getString("nama")
        val deskripsi = arguments?.getString("deskripsi")
        val lokasiTahun = arguments?.getString("lokasiTahun")
        val gambar = arguments?.getInt("gambar") ?: 0

        binding.tvNamaDetail.text = nama
        binding.tvDeskripsiDetail.text = deskripsi
        binding.tvLokasiDetail.text = lokasiTahun
        binding.imgDetail.setImageResource(gambar)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}