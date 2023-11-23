package com.example.worldex.ui.dex.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.worldex.databinding.ItemDexBinding
import com.example.worldex.domain.model.DexInfo

class DexViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDexBinding.bind(view)

    fun render(dexInfo: DexInfo, onItemSelected: (DexInfo) -> Unit) {
        val context: Context = binding.tvTitle.context
        binding.ivPokemon.setImageResource(dexInfo.img)
        binding.tvTitle.text = context.getString(dexInfo.name)

        binding.parent.setOnClickListener {
            onItemSelected(dexInfo)
        }
    }


}