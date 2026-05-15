package com.example.recyclerview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemWisataBinding

class WisataAdapter(
    private val listWisata: List<Wisata>,
    private val onDetailClick: (Wisata) -> Unit,
    private val onMapsClick: (Wisata) -> Unit
) : RecyclerView.Adapter<WisataAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ItemWisataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val wisata = listWisata[position]
        with(holder.binding) {
            imgWisata.setImageResource(wisata.gambarRes)
            tvNama.text = wisata.nama
            tvTahun.text = wisata.lokasiTahun
            tvDeskripsi.text = wisata.deskripsi

            btnMaps.setOnClickListener {
                onMapsClick(wisata)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(wisata.gmapsUrl))
                holder.itemView.context.startActivity(intent)
            }

            btnDetail.setOnClickListener {
                onDetailClick(wisata)
                val bundle = Bundle().apply {
                    putString("nama", wisata.nama)
                    putString("deskripsi", wisata.deskripsi)
                    putString("lokasiTahun", wisata.lokasiTahun)
                    putInt("gambar", wisata.gambarRes)
                }
                Navigation.findNavController(it).navigate(
                    R.id.action_wisataListFragment_to_wisataDetailFragment,
                    bundle
                )
            }
        }
    }

    override fun getItemCount(): Int = listWisata.size
}