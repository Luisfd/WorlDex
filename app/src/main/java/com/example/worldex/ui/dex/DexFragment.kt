package com.example.worldex.ui.dex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.worldex.R
import com.example.worldex.databinding.FragmentDexBinding
import com.example.worldex.domain.model.DexInfo
import com.example.worldex.domain.model.DexInfo.*
import com.example.worldex.domain.model.DexModel
import com.example.worldex.ui.dex.adapter.DexAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DexFragment : Fragment() {

    private val dexViewModel by viewModels<DexViewModel>()
    private lateinit var dexAdapter: DexAdapter

    private var _binding: FragmentDexBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDexBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        dexAdapter = DexAdapter(onItemSelected = {
            val type:DexModel = when(it){
                Bulbasaur -> DexModel.Bulbasaur
                Ivysaur -> DexModel.Ivysur
                Venusaur -> DexModel.Venusur
                Charmander -> DexModel.Charmander
                }
            findNavController().navigate(
                DexFragmentDirections.actionDexFragmentToDexListAtivity(type)
            )
        })

        binding.rvDex.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = dexAdapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dexViewModel.dex.collect {
                    //Cambios en dex
                    dexAdapter.updateList(it)
                }
            }
        }
    }



}