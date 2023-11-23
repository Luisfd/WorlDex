package com.example.worldex.ui.dex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worldex.R
import com.example.worldex.domain.model.DexInfo


class DexAdapter(private var dexList:List<DexInfo> = emptyList(), private val onItemSelected:(DexInfo)->Unit):
    RecyclerView.Adapter<DexViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DexViewHolder {
        return DexViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dex, parent, false))
    }

    override fun getItemCount() = dexList.size

    override fun onBindViewHolder(holder: DexViewHolder, position: Int) {
        holder.render(dexList[position], onItemSelected)
    }

    fun updateList(list: List<DexInfo>){
        dexList = list
        notifyDataSetChanged() //Solo para actualizar una vez, cuidado no eficiente
    }

}