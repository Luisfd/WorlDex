package com.example.worldex.ui.DexList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.worldex.databinding.ActivityDexListAtivityBinding
import com.example.worldex.domain.model.DexInfo
import com.example.worldex.domain.model.DexModel
import com.example.worldex.ui.DexList.adapter.DexListAdapter
import com.example.worldex.ui.dex.DexFragmentDirections
import com.example.worldex.ui.dex.adapter.DexAdapter
import com.example.worldex.ui.home.MainActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DexListAtivity : AppCompatActivity() {

    private lateinit var binding: ActivityDexListAtivityBinding
    private val dexListViewModel: DexListViewModel by viewModels()

    private lateinit var dexListAdapter: DexListAdapter

    private val args: DexListAtivityArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDexListAtivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        dexListViewModel.getListPokemon(args.type)
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }


    private fun initListeners() {
        //binding.ivBack.setOnClickListener { onBackPressed() }
    }


    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dexListViewModel.state.collect {
                    when (it) {
                        DexListState.Loading -> loadingState()
                        is DexListState.Error -> errorStateTODO()
                        is DexListState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorStateTODO() {
       binding.pb.isVisible = false
    }

    private fun successState(state: DexListState.Success) {

        binding.pb.isVisible = false

        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dexListViewModel.state.collect {
                    //Cambios en dex
                    dexListAdapter.updateList(state.list)
                }
            }
        }

        initList()

    }

    private fun initList() {
        dexListAdapter = DexListAdapter(onItemSelected = {

            lifecycleScope.launch() {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    binding.rvPokDex.isVisible = false

                    binding.ivPokemon.isVisible=true

                    Picasso.get().load(it.img).into(binding.ivPokemon);

                    delay(2000)

                    binding.ivPokemon.isVisible=false

                    binding.rvPokDex.isVisible = true
                }
            }

            /*val intent = Intent(this@DexListAtivity, MainActivity::class.java)
            startActivity(intent)*/
        })

        binding.rvPokDex.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = dexListAdapter
        }
    }

    @Override
    override fun onBackPressed() {
        // start your activity by passing the intent
        startActivity(Intent(this, MainActivity::class.java))
        super.onBackPressed()
    }
}