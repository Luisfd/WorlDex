package com.example.worldex.ui.DexList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worldex.R
import com.example.worldex.domain.model.DexInfo
import com.example.worldex.domain.model.PokeModel
import com.example.worldex.ui.dex.adapter.DexViewHolder

class DexListAdapter(private var dexList:List<PokeModel> = emptyList(), private val onItemSelected:(PokeModel)->Unit):
    RecyclerView.Adapter<DexListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DexListViewHolder {
        return DexListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dex, parent, false))
    }

    override fun getItemCount() = dexList.size

    override fun onBindViewHolder(holder: DexListViewHolder, position: Int) {
        holder.render(dexList[position], onItemSelected)
    }

    fun updateList(list: List<PokeModel>){
        dexList = list
        notifyDataSetChanged() //Solo para actualizar una vez, cuidado no eficiente
    }

}