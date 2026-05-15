package com.example.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.databinding.FragmentWisataListBinding
import kotlinx.coroutines.launch

class WisataListFragment : Fragment() {

    private var _binding: FragmentWisataListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WisataViewModel by viewModels {
        WisataViewModelFactory("Daftar Wisata")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWisataListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.wisataList.collect { list ->
                    if (list.isNotEmpty()) {
                        setupRecyclerViews(list)
                    }
                }
            }
        }
    }

    private fun setupRecyclerViews(list: List<Wisata>) {
        val adapterWisata = WisataAdapter(
            list,
            onDetailClick = { viewModel.logDetailClicked(it.nama) },
            onMapsClick = { viewModel.logMapsClicked(it.nama) }
        )

        binding.rvWisata.layoutManager = LinearLayoutManager(context)
        binding.rvWisata.adapter = adapterWisata

        binding.rvFeatured.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvFeatured.adapter = adapterWisata
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}