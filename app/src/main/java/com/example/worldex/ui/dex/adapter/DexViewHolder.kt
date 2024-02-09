package com.example.worldex.ui.dex.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.worldex.databinding.ItemDexBinding
import com.example.worldex.domain.model.DexInfo
import com.squareup.picasso.Picasso

class DexViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDexBinding.bind(view)

    fun render(dexInfo: DexInfo, onItemSelected: (DexInfo) -> Unit) {
        Picasso.get().load(dexInfo.img).into(binding.ivPokemon);
        binding.tvTitle.text = dexInfo.name

        binding.parent.setOnClickListener {
            onItemSelected(dexInfo)
        }
    }


}