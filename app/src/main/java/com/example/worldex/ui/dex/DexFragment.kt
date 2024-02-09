package com.example.worldex.ui.dex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
import com.example.worldex.ui.DexList.DexListState
import com.example.worldex.ui.DexList.adapter.DexListAdapter
import com.example.worldex.ui.dex.adapter.DexAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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
        dexViewModel.getListPokedex()
        initUI()
    }

    private fun initUI() {
        //initList()
        initUIState()
    }

    /*
    private fun initList() {
        dexAdapter = DexAdapter(onItemSelected = {
            val type:DexModel = when(it){
                Bulbasaur -> DexModel.Bulbasaur
                Ivysaur -> DexModel.Ivysaur
                Venusaur -> DexModel.Venusaur
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
    }*/

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dexViewModel.state.collect {
                    when (it) {
                        DexState.Loading -> loadingState()
                        is DexState.Error -> errorStateTODO()
                        is DexState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pbdex.isVisible = true
    }

    private fun errorStateTODO() {
        binding.pbdex.isVisible = false
    }

    private fun successState(state: DexState.Success) {

        binding.pbdex.isVisible = false

        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dexViewModel.state.collect {
                    //Cambios en dex
                    dexAdapter.updateList(state.listPokedex)
                }
            }
        }

        initList()

    }

    private fun initList() {
        dexAdapter = DexAdapter(onItemSelected = {

            lifecycleScope.launch() {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    findNavController().navigate(
                        DexFragmentDirections.actionDexFragmentToDexListAtivity(it.id)
                    )
                }
            }

            /*val intent = Intent(this@DexListAtivity, MainActivity::class.java)
            startActivity(intent)*/
        })

        binding.rvDex.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = dexAdapter
        }
    }



}