package com.example.coinapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coinapplication.Dto.CoinDetails
import com.example.coinapplication.adapter.CoinAdapater
import com.example.coinapplication.databinding.ActivityMainBinding
import com.example.coinapplication.network.NetworkResource
import com.example.coinapplication.repository.MainRepository
import com.example.coinapplication.viewModel.MainViewModel
import com.example.coinapplication.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private lateinit var myAdapater: CoinAdapater
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myAdapater = CoinAdapater(this)


        with(binding.mainRecylerView) {
            layoutManager = GridLayoutManager(this@MainActivity,3)
            adapter = myAdapater
        }

        val mainRepository = MainRepository()
        val viewModelFactory = MainViewModelFactory(mainRepository)

        viewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]


        viewModel.coinResponse.observe(this, Observer { response ->

            when(response) {
                is NetworkResource.Success -> {
                    response.data?.let {
                       myAdapater.differ.submitList(it.data.list)
                    }
                }
                else -> {}
            }

        })


    }

    private fun setAdapater(data:ArrayList<CoinDetails>){
        val adapater = CoinAdapater(this)
        binding.mainRecylerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity,3)
            adapter = adapater
        }
    }
}