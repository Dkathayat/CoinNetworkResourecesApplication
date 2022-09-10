package com.example.coinapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coinapplication.repository.MainRepository

class MainViewModelFactory (private val mainRepository: MainRepository): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.mainRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}