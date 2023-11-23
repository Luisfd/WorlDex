package com.example.worldex.ui.DexList.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.worldex.data.providers.DexProvider
import com.example.worldex.databinding.ActivityDexListAtivityBinding
import com.example.worldex.databinding.ItemDexBinding
import com.example.worldex.domain.model.DexInfo
import com.example.worldex.domain.model.PokeModel
import com.squareup.picasso.Picasso


class DexListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDexBinding.bind(view)

    fun render(pokeModel: PokeModel, onItemSelected: (PokeModel) -> Unit) {


        Picasso.get().load(pokeModel.img).into(binding.ivPokemon);
        binding.tvTitle.text = pokeModel.name

        binding.parent.setOnClickListener {
            onItemSelected(pokeModel)
        }
    }

}