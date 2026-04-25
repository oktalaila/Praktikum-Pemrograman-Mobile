package com.example.recyclerview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemWisataBinding

class WisataAdapter(private val listWisata: List<Wisata>) :
    RecyclerView.Adapter<WisataAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ItemWisataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val rv = parent as RecyclerView
        val lm = rv.layoutManager as? LinearLayoutManager

        if (lm?.orientation == LinearLayoutManager.HORIZONTAL) {
            val screenWidth = parent.context.resources.displayMetrics.widthPixels
            val params = binding.root.layoutParams as ViewGroup.MarginLayoutParams

            params.width = (screenWidth * 0.86).toInt()
            params.marginEnd = (12 * parent.context.resources.displayMetrics.density).toInt()
            params.marginStart = 0
            binding.root.layoutParams = params
        } else {
            val params = binding.root.layoutParams as ViewGroup.MarginLayoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.marginStart = 0
            params.marginEnd = 0
            binding.root.layoutParams = params
        }

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
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(wisata.gmapsUrl))
                holder.itemView.context.startActivity(intent)
            }

            btnDetail.setOnClickListener {
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